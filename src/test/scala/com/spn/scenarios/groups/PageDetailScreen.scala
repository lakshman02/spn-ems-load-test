package com.spn.scenarios.groups

import com.jayway.jsonpath._
import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.{BundleIdRequest, EpgReminderGetListRequest, EpisodeDetailRequest, GroupOfBundlesRequest, MovieDetailRequest, ShowDetailRequest, VODDetailsRequest}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object PageDetailScreen {

  def setTheUrlIdToSession(session: Session, contentType: String, contentSubtype: String,parentType : String, contentIdKey : String): Session = {

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
      session.set(contentIdKey, finalIdToNavigateTo)
    } else {
      println(s"\nAll attempts failed, for '$contentType' & '$contentSubtype'")
      session
    }
  }


  val openVODDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","","","contentId")
  }).doIf(session => session.contains("contentId" )){
    exec(VODDetailsRequest.vodDetails)
  }

  val openMovieDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","MOVIE","","movieId")
  }).doIf(session => session.contains("movieId" )){
    exec(MovieDetailRequest.movieDetail)
  }

  val openShowDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","SHOW","","Group_Of_Bundle")
  }).doIf(session => session.contains("Group_Of_Bundle" )){
    exec(ShowDetailRequest.showDetailRequest)
  }

  val openEpisodeDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","EPISODE","","episodeid")
  }).doIf(session => session.contains("episodeid" )){
    exec(EpisodeDetailRequest.Episode_Detail)
  }

  val openBundleDetails = exec(session => {
    setTheUrlIdToSession(session, "","","BUNDLE","bundleId")
  }).doIf(session => session.contains("bundleId" )){
    exec(BundleIdRequest.BundleId)
  }

  val openGroupOfBundlesDetails = exec(session => {
    setTheUrlIdToSession(session, "","","GROUP_OF_BUNDLES","groupBundleId")
  }).doIf(session => session.contains("groupBundleId")){
    exec(GroupOfBundlesRequest.groupOfBundles)
  }

  val openEpgList = exec(session => {
    session.set("channelId", "ALL")
    session.set("offset","100")
    session.set("startDate", s"${CommonFeedFiles.dateTimeFeeder}")
    session.set("from","0")
    session.set("size", "10")
  }).exec(EpgReminderGetListRequest.EPG_GetList)



  val openDetailsPage = randomSwitch(
    20d -> openVODDetails,
    10d -> openMovieDetails,
    10d -> openShowDetails,
    10d -> openEpisodeDetails,
    10d -> openBundleDetails,
    10d -> openGroupOfBundlesDetails,
    10d -> openEpgList
  )

  val guestUserDetailScreenScenario = exec(openDetailsPage)

}
