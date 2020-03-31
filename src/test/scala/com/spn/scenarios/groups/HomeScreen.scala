package com.spn.scenarios.groups


import java.time.LocalDateTime

import com.spn.common.Constants
import com.spn.requests.{AddListRequest, AddReminderRequest, DeleteListRequest, DeleteReminderRequest, EpgReminderDeleteRequest, EpgReminderGetListRequest, EpgReminderRequest, GetListRequest, GetPageIdRequest, GetProfileRequest, GetRemindersRequest, GetXDRRequest, UserRecommendationLandingRequest}
import com.spn.scenarios.groups.UserAppLaunchScenario.setRandomPageURLToSession
import com.spn.scenarios.groups.PageDetailScreen.{setTheUrlIdToSession,extractFixtureDetailsToSession}
import io.gatling.core.Predef._

object HomeScreen {

  val openEpgList = exec(session => {
    session.set("channelId", "ALL")
      .set("offset","100")
      .set("getDateTime", s"${LocalDateTime.now()}")
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
    exec(AddListRequest.addList).exec(DeleteListRequest.deleteList)
  }

  val mYListDistribution = randomSwitch(2d -> openAddMyList)

  val addFixtureReminder = exec(session => {
    extractFixtureDetailsToSession(session,"contentId","matchId")
      .set("startDateTime",s"${System.currentTimeMillis()}")
  }).doIf(session => session.contains("contentId") && session.contains("matchId")){
    exec(AddReminderRequest.addReminder)
      .exec(GetRemindersRequest.getRemindersRequest)
      .exec(DeleteReminderRequest.deleteReminderRequest)
  }

  val fixtureDistribution = randomSwitch(10d -> addFixtureReminder)

  val userRecommendationLanding = exec(session => {
    setRandomPageURLToSession(session, "home", "Home")
  })exec(UserRecommendationLandingRequest.userRecommendationLandingRequest)

  val addEPGReminder = exec(session => {
    setTheUrlIdToSession(session, "VOD","","","contentId")
  }).doIf(session => session.contains("contentId")){
    exec(EpgReminderRequest.epgReminder).exec(EpgReminderDeleteRequest.epgReminderDelete)
  }

  val epgReminderDistribution = randomSwitch(2d -> addEPGReminder)


  val guestUserHomeScreenScenario = doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(openHomePageAgain)
    .exec(openEpgList)
  }

  val loggedInUserHomeScreenScenario = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)
    && session.contains(Constants.RESP_AUTH_TOKEN)) {

    exec(openHomePageAgain)
      .exec(openEpgList)
      .exec(GetListRequest.getUserListRequest)
      .exec(GetXDRRequest.getXDR)
//      .exec(PageDetailScreen.openTrayRecommendationRecosenseList) // TODO - Commented as per the latest comms from Accenture
//      .exec(PageDetailScreen.openTrayRecommendationCatchMediaList)
      .exec(mYListDistribution)
      .exec(fixtureDistribution) //TODO fix this - not working
      // .exec(epgReminderDistribution) //TODO fix this - not working
    // .exec(userRecommendationLanding) //TODO fix this - not working
  }
}
