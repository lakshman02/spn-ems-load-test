package com.spn.scenarios.groups

import java.time.LocalDateTime

import com.jayway.jsonpath._
import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.{AddListRequest, BundleIdRequest, DeleteListRequest, EpgReminderGetListRequest, EpisodeDetailRequest, GetXDRRequest, GroupOfBundlesRequest, IsSubscribedRequest, MovieDetailRequest, ShowDetailRequest, TrayRecommendationCatchMediaRequest, TrayRecommendationRecosenseRequest, VODDetailsRequest, VideoUrlRequest}
import com.spn.scenarios.TrayRecommendationRecosenseScenario
import io.gatling.commons.validation.Validation
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

  def extractFixtureDetailsToSession(session: Session): Session = {

    val pageResponse = session(Constants.RESP_PAGE_RESPONSE).as[String]
    println(s"\nextractFixtureDetailsToSession : pageResponse : $pageResponse")

//    val expressionForContentId = "$.containers[*].assets.containers.containers.containers[?(@..contentSubtype=='LIVE_SPORT')&&(@..matchid !='')].metadata.contentId"
    val expressionForContentId = "$.containers[*].assets.containers[*].containers.containers[?(@.metadata.objectSubtype == 'LIVE_SPORT')].metadata.contentId"
    println(s"\nextractFixtureDetailsToSession : expressionForContentId : $expressionForContentId")

//    val expressionForMatchId = "$.containers[?(@..matchid !='' && @..contentSubtype=='LIVE_SPORT')].assets.containers[*]..metadata.emfAttributes.matchid"
    val expressionForMatchId = "$.containers[*].assets.containers[*].containers.containers[?(@.metadata.objectSubtype == 'LIVE_SPORT')].metadata.emfAttributes.matchid"
    println(s"\nextractFixtureDetailsToSession : expressionForMatchId : $expressionForMatchId")

    val context = JsonPath.parse(pageResponse)

    val contentIdFound = context.read[JSONArray](expressionForContentId)
    println(s"\nextractFixtureDetailsToSession : Content id is : $contentIdFound")

    val matchIdFound = context.read[JSONArray](expressionForMatchId)
    println(s"\nextractFixtureDetailsToSession : Match id is : $matchIdFound")

    // Cherry picking a url to navigate to
    var finalIdToNavigateTo = ""
    if (contentIdFound != null && contentIdFound.size() >= 1) {
      finalIdToNavigateTo = contentIdFound.get(0).toString
    }

    var finalMatchIdToNavigateTo = ""
    if (matchIdFound != null && matchIdFound.size() >= 1) {
      finalMatchIdToNavigateTo = matchIdFound.get(0).toString
    }

    println(s"\nextractFixtureDetailsToSession : Final Content id to Navigate To is : $finalIdToNavigateTo")
    println(s"\nextractFixtureDetailsToSession : Final match id to Navigate To is : $finalMatchIdToNavigateTo")

    if ((finalIdToNavigateTo != null && !finalIdToNavigateTo.isEmpty) && (finalMatchIdToNavigateTo != null && !finalMatchIdToNavigateTo.isEmpty)) {
      session.set("contentId", finalIdToNavigateTo)
        .set("matchId",finalMatchIdToNavigateTo)
    } else {
      println(s"\nAll attempts failed to fetch fixture details")
      session
    }
  }

  def setContentDetailsToSession(session: Session,value : String): Session = {

    val detailPageResponse = session(Constants.RESP_DETAIL_PAGE_RESPONSE).as[String]
    println(s"\nsetContentDetailsToSession : detailPageResponse : $detailPageResponse")

    val expressionForId = "$.resultObj.containers[?(@.metadata.emfAttributes.value =='"+value+"')].metadata.contentId"
    println(s"\nsetContentDetailsToSession : expressionForId : $expressionForId")

    val expressionForTitle = "$.resultObj.containers[?(@.metadata.emfAttributes.value =='"+value+"')].metadata.title"
    println(s"\nsetContentDetailsToSession : expressionForTitle : $expressionForTitle")

    val expressionForType = "$.resultObj.containers[?(@.metadata.emfAttributes.value =='"+value+"')].metadata.contentSubtype"
    println(s"\nsetContentDetailsToSession : expressionForType : $expressionForType")

    val context = JsonPath.parse(detailPageResponse)

    val contentIdFound = context.read[JSONArray](expressionForId)
    println(s"\nsetContentDetailsToSession : content id is : $contentIdFound")

    val titleFound = context.read[JSONArray](expressionForTitle)
    println(s"\nsetContentDetailsToSession : title is : $titleFound")

    val typeFound = context.read[JSONArray](expressionForType)
    println(s"\nsetContentDetailsToSession : type is : $typeFound")

    // Cherry picking a url to navigate to
    var finalIdToNavigateTo = ""
    if (contentIdFound != null && contentIdFound.size() >= 1) {
      finalIdToNavigateTo = contentIdFound.get(0).toString
    }

    var finalTitleToNavigateTo = ""
    if (titleFound != null && titleFound.size() >= 1) {
      finalTitleToNavigateTo = titleFound.get(0).toString
    }

    var finalTypeToNavigateTo = ""
    if (typeFound != null && typeFound.size() >= 1) {
      finalTypeToNavigateTo = typeFound.get(0).toString
    }

    println(s"\nsetContentDetailsToSession : Final Content id to Navigate To is : $finalIdToNavigateTo")
    println(s"\nsetContentDetailsToSession : Final title to Navigate To is : $finalTitleToNavigateTo")
    println(s"\nsetContentDetailsToSession : Final type to Navigate To is : $finalTypeToNavigateTo")


    if ((finalIdToNavigateTo != null && !finalIdToNavigateTo.isEmpty) && (finalTitleToNavigateTo != null && !finalTitleToNavigateTo.isEmpty)
    && (finalTypeToNavigateTo != null && !finalTypeToNavigateTo.isEmpty)) {
      session.set("TVODID", finalIdToNavigateTo).set("ShowType",finalTypeToNavigateTo).set("ShowName",finalTitleToNavigateTo)
    } else {
      println(s"\nAll attempts failed to fetch fixture details")
      session
    }
  }



  val openVideoUrl = exec(session =>{
    setTheUrlIdToSession(session,"VOD","","","contentId")
  }).doIf(session => session.contains("contentId" )) {
    exec(VideoUrlRequest.videoUrl)
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
  }).exec(TrayRecommendationCatchMediaRequest.trayRecommendationCatchMediaRequest)

  // TODO - this needs further breakup like Under VOD comes Movie, show and Episode
  val openDetailsPage = randomSwitch(
    10d -> VODDistribution,
    10d -> openBundleDetails,
    10d -> openEpgList,
    50d -> openVideoUrl,
    10d -> openTrayRecommendationCatchMediaList,
    10d -> openTrayRecommendationRecosenseList
  )

  // Below methods and values are related to Logged in User

  val openAddMyList = exec(session => {
    setTheUrlIdToSession(session, "VOD","","","assetID")
  }).doIf(session => session.contains("assetID")){
    exec(AddListRequest.addList)
  }

//  val openIsSubscribed = exec(session => {
//    setContentDetailsToSession(session,"SVOD")
//      .set("channelPartnerID","MSMIND1")
//      .set("getDateTime","${getDateTime}")
//  }).doIf(session => session.contains("TVODID") && session.contains("ShowType") && session.contains("ShowName")) {
//    exec(IsSubscribedRequest.isSubscribed)
//  }

  val doNavigateToGuestUserDetailsPage = exec(openDetailsPage)

  val doNavigateToLoggedInUserDetailsPage = doIf(session => session.contains((Constants.RESP_AUTH_TOKEN)) && session.contains(Constants.RESP_SECURITY_TOKEN)){
    exec(openAddMyList)
      .exec(GetXDRRequest.getXDR)
     .exec(HomeScreen.fixtureDistribution)
      .exec(HomeScreen.openEpgList)
      .exec(HomeScreen.epgReminderDistribution)
   //   .exec(VODDistribution)
      .exec(openVideoUrl)
    //  .exec(openIsSubscribed)
      .exec(openTrayRecommendationCatchMediaList)
      .exec(openTrayRecommendationRecosenseList)
   //   .exec(HomeScreen.userRecommendationLanding)
  }
}
