package com.spn.scenarios.groups

import java.time.LocalDateTime

import com.jayway.jsonpath._
import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.{AddListRequest, AddReminderRequest, BundleIdRequest, DeleteListRequest, DeleteReminderRequest, DetailsForEpisodeMovieShowRequest, EpgReminderGetListRequest, EpisodeDetailRequest, GetRemindersRequest, GetXDRRequest, GroupOfBundlesRequest, IsSubscribedRequest, MovieDetailRequest, ShowDetailRequest, TrayRecommendationCatchMediaRequest, TrayRecommendationRecosenseRequest, UserRecommendationRequest, VODDetailsRequest, VideoUrlRequest}
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

  def setContentDetailsToSession(session: Session): Session = {

    val detailPageResponse = session(Constants.RESP_DETAIL_PAGE_RESPONSE).as[String]
    println(s"\nsetContentDetailsToSession : detailPageResponse : $detailPageResponse")

   // val expressionForId = "$.containers[?(@.metadata.emfAttributes.value =='"+value+"')].metadata.contentId"
    val expressionForId = "$.containers[*].metadata.contentId"
    println(s"\nsetContentDetailsToSession : expressionForId : $expressionForId")

   // val expressionForTitle = "$.containers[?(@.metadata.emfAttributes.value =='"+value+"')].metadata.title"
    val expressionForTitle = "$.containers[*].metadata.title"
    println(s"\nsetContentDetailsToSession : expressionForTitle : $expressionForTitle")

   // val expressionForType = "$.containers[?(@.metadata.emfAttributes.value =='"+value+"')].metadata.contentSubtype"
    val expressionForType = "$.containers[*].metadata.contentSubtype"
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
    setTheUrlIdToSession(session,"VOD","CLIP","","contentId")
  }).doIf(session => session.contains("contentId" )) {
    exec(VideoUrlRequest.videoUrl)
  }

  val openVODDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","","","contentId")
  }).doIf(session => session.contains("contentId" )){
    exec(VODDetailsRequest.vodDetails)
  }

  val openMovieDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","MOVIE","","contentId")
      .set("details_type","Movie")
  }).doIf(session => session.contains("contentId" )){
    exec(DetailsForEpisodeMovieShowRequest.detailsForEpisodeMovieShowRequest)
  }

  val openShowDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","SHOW","","contentId")
      .set("details_type","Show")
  }).doIf(session => session.contains("contentId" )){
    exec(DetailsForEpisodeMovieShowRequest.detailsForEpisodeMovieShowRequest)
  }

  val openEpisodeDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","EPISODE","","contentId")
      .set("details_type","Episode")
  }).doIf(session => session.contains("contentId" )){
    exec(DetailsForEpisodeMovieShowRequest.detailsForEpisodeMovieShowRequest)
  }

  val openBundleDetails = exec(session => {
    setTheUrlIdToSession(session, "","","BUNDLE","bundleId")
  }).doIf(session => session.contains("bundleId" )){
    exec(BundleIdRequest.BundleId)
  }

  val openDetailDistribution = randomSwitch(
    50d -> openMovieDetails,
    25d -> openEpisodeDetails,
    25d -> openShowDetails)

  val openEpgList = exec(session => {
    session.set("channelId", "ALL")
      .set("offset","100")
      .set("getDateTime", s"${LocalDateTime.now()}")
    .set("from","0")
    .set("size", "10")
  }).exec(EpgReminderGetListRequest.EPG_GetList)

  val getEpgListDistribution = randomSwitch(30d -> openEpgList)


  val openTrayRecommendationRecosenseList = exec(session => {
    session.set("recommendationType", "recosense")
  }).exec(TrayRecommendationRecosenseRequest.trayRecommendationRecosenseRequest)

  val openTrayRecommendationCatchMediaList = exec(session => {
    session.set("recommendationType", "catchmedia")
  }).exec(TrayRecommendationCatchMediaRequest.trayRecommendationCatchMediaRequest)


  // Below methods and values are related to Logged in User

  val openAddMyList = exec(session => {
    setTheUrlIdToSession(session, "VOD","","","assetID")
  }).doIf(session => session.contains("assetID")){
    exec(AddListRequest.addList)
  }

  val distributionForAddingList = randomSwitch(30d -> openAddMyList)

  val executeFixtureReminder = exec(session => {
    extractFixtureDetailsToSession(session)
      .set("startDateTime",s"${System.currentTimeMillis() - 10000000}")
  }).doIf(session => session.contains("contentId") && session.contains("matchId")){
    exec(AddReminderRequest.addReminder)
      .randomSwitch(30d -> exec(DeleteReminderRequest.deleteReminderRequest))
  }

  val distributionForFixtures = randomSwitch(25d -> executeFixtureReminder)

  val openIsSubscribed = exec(session => {      //TODO Until phase 2, where we will check for value TVOD
    setContentDetailsToSession(session)
      .set("isContent","false")
  }).doIf(session => session.contains("TVODID") && session.contains("ShowType") && session.contains("ShowName") && session.contains("isContent")) {
    exec(IsSubscribedRequest.isSubscribed)
  }

  val distributionForSubscribed = randomSwitch(25d -> openIsSubscribed)

  val distributionForTrayRecommendation = randomSwitch(50d -> openTrayRecommendationRecosenseList,
    50d -> openTrayRecommendationCatchMediaList)

  val doNavigateToGuestUserDetailsPage =
    exec(openVODDetails)
    .exec(openDetailDistribution)
    .exec(openBundleDetails)
    .exec(openVideoUrl)
    .exec(HomeScreen.epgReminderDistribution)
    .exec(distributionForTrayRecommendation)

  val doNavigateToLoggedInUserDetailsPage = doIf(session => session.contains((Constants.RESP_AUTH_TOKEN)) && session.contains(Constants.RESP_SECURITY_TOKEN)){
    exec(openVODDetails)
      .exec(openDetailDistribution)
      .exec(openVideoUrl)
      .exec(openBundleDetails)
      .exec(distributionForAddingList)
      .exec(GetXDRRequest.getXDR)
     .exec(distributionForFixtures)
      .exec(HomeScreen.epgReminderDistribution)
      .exec(distributionForSubscribed)
      .exec(openTrayRecommendationCatchMediaList)
      .exec(openTrayRecommendationRecosenseList)
      .exec(HomeScreen.userRecommendationLanding)
  }
}
