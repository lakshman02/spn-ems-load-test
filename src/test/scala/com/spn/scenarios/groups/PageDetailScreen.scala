package com.spn.scenarios.groups

import com.jayway.jsonpath._
import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.{BundleIdRequest, EpisodeDetailRequest, GroupOfBundlesRequest, MovieDetailRequest, ShowDetailRequest, VODDetailsRequest}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object PageDetailScreen {

  def setTheUrlIdToSession(session: Session, contentType: String, contentSubtype: String,parentType : String): Session = {

    val pageResponse = session(Constants.RESP_PAGE_RESPONSE).as[String]
    println(s"\npageResponse : $pageResponse")

    var expression = ""

    if(contentSubtype.isEmpty || contentSubtype==null){
      expression = "$.containers[*].assets.containers[?(@.metadata.contentType == '"+contentType+"')].id"
      println(s"\nExpression : $expression")

    }else if((contentSubtype.isEmpty || contentSubtype==null) && (contentType.isEmpty || contentType ==null)){
      expression = "$.containers[*].assets.containers[*].parents[?(@.parentType == '"+parentType+"')].parentId"
      println(s"\nExpression : $expression")

    }else {
      expression = "$.containers[*].assets.containers[?(@.metadata.contentType == '"+contentType+"' && @.metadata.contentSubtype == '"+contentSubtype+"')].id"
      println(s"\nExpression : $expression")
    }


    val context = JsonPath.parse(pageResponse)
    val contentIdFound = context.read[JSONArray](expression)

    // Cherry picking a url to navigate to
    var finalIdToNavigateTo = ""
    if (contentIdFound != null && contentIdFound.size() == 1) {
      finalIdToNavigateTo = contentIdFound.get(0).toString
    } else if (contentIdFound != null && contentIdFound.size() > 1) {
      val size = contentIdFound.size()
      finalIdToNavigateTo = contentIdFound.get(Random.nextInt(size - 1)).toString
    }

    println(s"\nFinal id to Navigate To for '$contentType' is : $finalIdToNavigateTo")

    if (finalIdToNavigateTo != null && !finalIdToNavigateTo.isEmpty) {
      session.set(Constants.RESP_CONTENT_ID, finalIdToNavigateTo)
    } else {
      println(s"\nAll attempts failed, for '$contentType' & '$contentSubtype'")
      session
    }
  }


  val openVODDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","","")
  }).doIf(session => session.contains(Constants.RESP_CONTENT_ID )){
    exec(VODDetailsRequest.vodDetails)
  }

  val openMovieDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","MOVIE","")
  }).doIf(session => session.contains(Constants.RESP_CONTENT_ID )){
    exec(MovieDetailRequest.movieDetail)
  }

  val openShowDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","SHOW","")
  }).doIf(session => session.contains(Constants.RESP_CONTENT_ID )){
    exec(ShowDetailRequest.showDetailRequest)
  }

  val openEpisodeDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","EPISODE","")
  }).doIf(session => session.contains(Constants.RESP_CONTENT_ID )){
    exec(EpisodeDetailRequest.Episode_Detail)
  }

  val openBundleDetails = exec(session => {
    setTheUrlIdToSession(session, "","","BUNDLE")
  }).doIf(session => session.contains(Constants.RESP_CONTENT_ID )){
    exec(BundleIdRequest.BundleId)
  }

  val openGroupOfBundlesDetails = exec(session => {
    setTheUrlIdToSession(session, "","","GROUP_OF_BUNDLES")
  }).doIf(session => session.contains(Constants.RESP_CONTENT_ID )){
    exec(GroupOfBundlesRequest.groupOfBundles)
  }


  val openDetailsPage = randomSwitch(
    20d -> openVODDetails,
    20d -> openMovieDetails,
    10d -> openShowDetails,
    10d -> openEpisodeDetails,
    20d -> openBundleDetails,
    20d -> openGroupOfBundlesDetails
  )

  val guestUserDetailScreenScenario =
          exec(openDetailsPage)

}
