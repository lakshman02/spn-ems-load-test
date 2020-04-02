package com.spn.scenarios.groups


import java.time.{LocalDate}
import java.time.format.DateTimeFormatter

import com.jayway.jsonpath.JsonPath
import com.spn.common.Constants
import com.spn.requests.{AddListRequest, AddReminderRequest, DeleteListRequest, DeleteReminderRequest, EpgReminderDeleteRequest, EpgReminderGetListRequest, EpgReminderRequest, GetListRequest, GetPageIdRequest, GetProfileRequest, GetRemindersRequest, GetXDRRequest, UserRecommendationLandingRequest}
import com.spn.scenarios.groups.UserAppLaunchScenario.setRandomPageURLToSession
import com.spn.scenarios.groups.PageDetailScreen.{extractFixtureDetailsToSession, setTheUrlIdToSession}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

object HomeScreen {

  val oneMonthBeforeCurrentDate = LocalDate.now().minusMonths(1)

  oneMonthBeforeCurrentDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))

  def setTheEPGValuesToSession(session: Session): Session = {

    val epgResponse = session(Constants.RESP_EPG_RESPONSE).as[String]
    println(s"\nepgResponse : $epgResponse")

    val expressionForChannelId = "$.containers[*].channelId"
      println(s"\nexpressionForChannelId : $expressionForChannelId")

    val expressionForAssetId = "$.containers[*].assetId"
    println(s"\nexpressionForAssetId : $expressionForAssetId")

    val expressionForTitle = "$.containers[*].title"
    println(s"\nexpressionForTitle : $expressionForTitle")

    val expressionForStartDateTime = "$.containers[*].startDateTime"
    println(s"\nexpressionForStartDateTime : $expressionForStartDateTime")

    val expressionForEndDateTime = "$.containers[*].endDateTime"
    println(s"\nexpressionForEndDateTime : $expressionForEndDateTime")

    val context = JsonPath.parse(epgResponse)
    val channelIdFound = context.read[JSONArray](expressionForChannelId)
    val assetIdFound = context.read[JSONArray](expressionForAssetId)
    val titleFound = context.read[JSONArray](expressionForTitle)
    val startDateTimeFound = context.read[JSONArray](expressionForStartDateTime)
    val endDateTimeFound = context.read[JSONArray](expressionForEndDateTime)

    // Cherry picking a url to navigate to
    var finalChannelIdToNavigateTo = ""
    if (channelIdFound != null && channelIdFound.size() >= 1) {
      finalChannelIdToNavigateTo = channelIdFound.get(0).toString
    }

    var finalAssetIdToNavigateTo = ""
    if (assetIdFound != null && assetIdFound.size() >= 1) {
      finalAssetIdToNavigateTo = assetIdFound.get(0).toString
    }

    var finalTitleToNavigateTo = ""
    if (titleFound != null && titleFound.size() >= 1) {
      finalTitleToNavigateTo = titleFound.get(0).toString
    }
    var finalstartDateTime = ""
    if (startDateTimeFound != null && startDateTimeFound.size() >= 1) {
      finalstartDateTime = startDateTimeFound.get(0).toString
    }

    var finalEndDateTime = ""
    if (endDateTimeFound != null && endDateTimeFound.size() >=1 ) {
      finalEndDateTime = endDateTimeFound.get(0).toString
    }
    println(s"\nFinal values to pass is : '$finalChannelIdToNavigateTo' & '$finalAssetIdToNavigateTo' & '$finalTitleToNavigateTo' & '$finalstartDateTime' & '$finalEndDateTime'")

    if ((finalChannelIdToNavigateTo != null && !finalChannelIdToNavigateTo.isEmpty)&&(finalAssetIdToNavigateTo != null && !finalAssetIdToNavigateTo.isEmpty)
    &&(finalTitleToNavigateTo != null && !finalTitleToNavigateTo.isEmpty)&&(finalstartDateTime != null && !finalstartDateTime.isEmpty)
    &&(finalEndDateTime != null && !finalEndDateTime.isEmpty)) {
      session.set("channelId", finalChannelIdToNavigateTo)
        .set("assetId",finalAssetIdToNavigateTo)
        .set("title",finalTitleToNavigateTo)
        .set("startDateTime",finalstartDateTime)
        .set("endDateTime",finalEndDateTime)
    } else {
      println(s"\nAll attempts failed, for '$finalChannelIdToNavigateTo' & '$finalAssetIdToNavigateTo' & '$finalTitleToNavigateTo' & '$finalstartDateTime' & '$finalEndDateTime'")
      session
    }
  }


  val openEpgList = exec(session => {
    session.set("channelId", "ALL")
      .set("offset","100")
      .set("getDateTime", s"${oneMonthBeforeCurrentDate}")
      .set("from","0")
      .set("size", "10")
  }).exec(EpgReminderGetListRequest.EPG_GetList)

  val openHomePageAgain = exec(session => {
    setRandomPageURLToSession(session, "home", "Home")
      .set("pageSuffix", "Logged In User - Navigating to top of page - p[0-5]")
      .set("paginationFrom", "0")
      .set("paginationTo", "5")
  }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
      exec(GetPageIdRequest.PageId)
  }

  val openAddMyList = exec(session => {
    setTheUrlIdToSession(session, "VOD","","","assetID")
  }).doIf(session => session.contains("assetID")){
    exec(AddListRequest.addList).
      randomSwitch(1d -> exec(DeleteListRequest.deleteList)) // Only 1% of users gets to delete
  }

  val mYListDistribution = randomSwitch(25d -> openAddMyList)

  val addFixtureReminder = exec(session => {
    extractFixtureDetailsToSession(session)
      .set("startDateTime",s"${System.currentTimeMillis() - 10000000}")
  }).doIf(session => session.contains("contentId") && session.contains("matchId")){
    exec(AddReminderRequest.addReminder)
      .randomSwitch(20d -> exec(GetRemindersRequest.getRemindersRequest),
        10d -> exec(DeleteReminderRequest.deleteReminderRequest))
  }

  val fixtureDistribution = randomSwitch(50d -> addFixtureReminder)

  val userRecommendationLanding = exec(session => {
    setRandomPageURLToSession(session, "home", "Home")
  }).doIf(session => session.contains("pageid")){
    exec(UserRecommendationLandingRequest.userRecommendationLandingRequest)
  }

  val addEPGReminder = exec(session => {
    setTheEPGValuesToSession(session)
  }).doIf(session => session.contains("channelId") && session.contains("assetId")
  && session.contains("title")&& session.contains("startDateTime")&&session.contains("endDateTime")){
    exec(EpgReminderRequest.epgReminder).exec(EpgReminderDeleteRequest.epgReminderDelete)
  }

  val epgReminderDistribution = randomSwitch(10d -> addEPGReminder)

  val doNavigateToGuestUserHomePage = doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(openHomePageAgain)
    .exec(openEpgList)
  }

  val doNavigateToLoggedInUserHomePage = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)
    && session.contains(Constants.RESP_AUTH_TOKEN)) {

    exec(openHomePageAgain)
      .exec(openEpgList)
      .exec(GetListRequest.getUserListRequest)
      .exec(GetXDRRequest.getXDR)
      .exec(mYListDistribution)
      .exec(fixtureDistribution) // TODO - Call the SI Fixture Get API for integrating here - Check with Kamraj (TE)
      .exec(epgReminderDistribution)
    //      .exec(PageDetailScreen.openTrayRecommendationRecosenseList) // TODO - Commented as per the latest comms from Accenture
    //      .exec(PageDetailScreen.openTrayRecommendationCatchMediaList)
    //     exec(userRecommendationLanding) // TODO - Commented as per the latest comms from Accenture
  }
}
