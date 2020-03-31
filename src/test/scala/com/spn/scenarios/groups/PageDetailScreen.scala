package com.spn.scenarios.groups

import java.time.LocalDateTime

import com.jayway.jsonpath._
import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.{BundleIdRequest, EpgReminderGetListRequest, EpisodeDetailRequest, GroupOfBundlesRequest, MovieDetailRequest, ShowDetailRequest, TrayRecommendationRecosenseRequest, VODDetailsRequest}
import com.spn.scenarios.TrayRecommendationRecosenseScenario
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object PageDetailScreen {

  def setTheUrlIdToSession(session: Session, contentType: String, contentSubtype: String,parentType : String, contentIdKey : String): Session = {

    val pageResponse = session(Constants.RESP_PAGE_RESPONSE).as[String]
    println(s"\npageResponse : $pageResponse")

    var expression = ""

    if(contentSubtype.isEmpty && parentType.isEmpty){
      expression = "$.containers[*].assets.containers[?(@.metadata.contentType == '"+contentType+"')].id"
      println(s"\nExpression : $expression")

    }else if(contentSubtype.isEmpty && contentType.isEmpty ){
      expression = "$.containers[*].assets.containers[*].parents[?(@.parentType == '"+parentType+"')].parentId"
      println(s"\nExpression : $expression")

    }else if(parentType.isEmpty) {
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
      println(s"\nAll attempts failed, for '$contentType' & '$contentSubtype' & '$parentType'")
      session
    }
  }

  def extractFixtureDetailsToSession(session: Session, contentIdKey : String, matchId : String): Session = {

    val pageResponse = session(Constants.RESP_PAGE_RESPONSE).as[String]
    println(s"\npageResponse : $pageResponse")


    val expressionForContentId = "$.containers[*].assets.containers[?(@..matchid !='')].containers.containers[?(@..contentSubtype=='LIVE_SPORT')].metadata.contentId"
      println(s"\nexpressionForContentId : $expressionForContentId")

    val expressionForMatchId = "$.containers[?(@..matchid !='' && @..contentSubtype=='LIVE_SPORT')].assets.containers[*]..metadata.emfAttributes.matchid"
    println(s"\nexpressionForMatchId : $expressionForMatchId")

    val context = JsonPath.parse(pageResponse)
    val contentIdFound = context.read[JSONArray](expressionForContentId)
    val matchIdFound = context.read[JSONArray](expressionForMatchId)

    // Cherry picking a url to navigate to
    var finalIdToNavigateTo = ""
    if (contentIdFound != null && contentIdFound.size() == 1) {
      finalIdToNavigateTo = contentIdFound.get(0).toString
    } else if (contentIdFound != null && contentIdFound.size() > 1) {
      finalIdToNavigateTo = contentIdFound.get(0).toString
    }

    var finalMatchIdToNavigateTo = ""
    if (matchIdFound != null && matchIdFound.size() == 1) {
      finalMatchIdToNavigateTo = matchIdFound.get(0).toString
    } else if (matchIdFound != null && matchIdFound.size() > 1) {
      finalMatchIdToNavigateTo = matchIdFound.get(0).toString
    }

    println(s"\nFinal id to Navigate To for  is : $finalIdToNavigateTo")

    if ((finalIdToNavigateTo != null && !finalIdToNavigateTo.isEmpty) && (finalMatchIdToNavigateTo != null && !finalMatchIdToNavigateTo.isEmpty)) {
      session.set(contentIdKey, finalIdToNavigateTo).set(matchId,finalMatchIdToNavigateTo)
    } else {
      println(s"\nAll attempts failed to fetch fixture details for")
      session
    }
  }

  def extractEPGDetailsToSession(session: Session, contentIdKey : String, matchId : String): Session = {

    val pageResponse = session(Constants.RESP_PAGE_RESPONSE).as[String]
    println(s"\npageResponse : $pageResponse")


    val expressionForContentId = "$.containers[*].assets.containers[?(@..matchid !='')].containers.containers[?(@..contentSubtype=='LIVE_SPORT')].metadata.contentId"
    println(s"\nexpressionForContentId : $expressionForContentId")

    val expressionForMatchId = "$.containers[?(@..matchid !='' && @..contentSubtype=='LIVE_SPORT')].assets.containers[*]..metadata.emfAttributes.matchid"
    println(s"\nexpressionForMatchId : $expressionForMatchId")

    val context = JsonPath.parse(pageResponse)
    val contentIdFound = context.read[JSONArray](expressionForContentId)
    val matchIdFound = context.read[JSONArray](expressionForMatchId)

    // Cherry picking a url to navigate to
    var finalIdToNavigateTo = ""
    if (contentIdFound != null && contentIdFound.size() == 1) {
      finalIdToNavigateTo = contentIdFound.get(0).toString
    } else if (contentIdFound != null && contentIdFound.size() > 1) {
      finalIdToNavigateTo = contentIdFound.get(0).toString
    }

    var finalMatchIdToNavigateTo = ""
    if (matchIdFound != null && matchIdFound.size() == 1) {
      finalMatchIdToNavigateTo = matchIdFound.get(0).toString
    } else if (matchIdFound != null && matchIdFound.size() > 1) {
      finalMatchIdToNavigateTo = matchIdFound.get(0).toString
    }

    println(s"\nFinal id to Navigate To for  is : $finalIdToNavigateTo")

    if ((finalIdToNavigateTo != null && !finalIdToNavigateTo.isEmpty) && (finalMatchIdToNavigateTo != null && !finalMatchIdToNavigateTo.isEmpty)) {
      session.set(contentIdKey, finalIdToNavigateTo).set(matchId,finalMatchIdToNavigateTo)
    } else {
      println(s"\nAll attempts failed to fetch fixture details for")
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

  val VODDistribution = randomSwitch(40d -> openVODDetails,
    20d -> openMovieDetails,
    20d -> openEpisodeDetails,
    20d -> openShowDetails)

  val openEpgList = exec(session => {
    session.set("channelId", "ALL")
      .set("offset","100")
      .set("getDateTime", s"${LocalDateTime.now()}")
    .set("from","0")
    .set("size", "10")
  }).exec(EpgReminderGetListRequest.EPG_GetList)


  val openTrayRecommendationRecosenseList = exec(session => {
    session.set("recommendationType", "recosense")
      .set("railType","you_may_like")
      .set("filter_contentSubtype", "SHOW")
  }).exec(TrayRecommendationRecosenseRequest.trayRecommendationRecosenseRequest)

  val openTrayRecommendationCatchMediaList = exec(session => {
    session.set("recommendationType", "catchmedia")
      .set("railType","cm_more_like_this")
      .set("filter_contentSubtype", "SHOW")
  }).exec(TrayRecommendationRecosenseRequest.trayRecommendationRecosenseRequest)

  // TODO - this needs further breakup like Under VOD comes Movie, show and Episode
  val openDetailsPage = randomSwitch(
    25d -> VODDistribution,
    10d -> openBundleDetails,
    25d -> openEpgList,
    15d -> openTrayRecommendationCatchMediaList,
    15d -> openTrayRecommendationRecosenseList
  )

  //val openDetailsPage = randomSwitch(100d -> openEpgList)

  val guestUserDetailScreenScenario = exec(openDetailsPage)

}
