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

  val openHomePage = exec(session => {
    // Where we are getting and setting Home URL

    var paginationFrom = 0;
    var paginationTo = 5;
    if(session.contains(Constants.RESP_COUNT_OF_ITEMS_IN_PAGE)) {
      val countOfItemsInPage = session(Constants.RESP_COUNT_OF_ITEMS_IN_PAGE).as[Int];
      val howMany = countOfItemsInPage / 5
      if(howMany > 1) {
        paginationFrom = paginationFrom + howMany
        paginationTo = paginationFrom + 5
      }
    }
    setRandomPageURLToSession(session, "home", "Home")
      .set("pageSuffix", s"Home Page Scrolling - p[${paginationFrom}-${paginationTo}]")
      .set("paginationFrom", paginationFrom)
      .set("paginationTo", paginationTo)
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
    exec(AddReminderRequest.addReminder).exec(DeleteReminderRequest.deleteReminderRequest)
  }

  val fixtureDistribution = randomSwitch(100d -> addFixtureReminder)

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
    exec(openHomePage)
    .exec(openEpgList)
  }

  val loggedInUserHomeScreenScenario = doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(guestUserHomeScreenScenario)
      .exec(GetProfileRequest.getProfile)
      .exec(GetListRequest.getUserListRequest)
      .exec(mYListDistribution)
      .exec(GetXDRRequest.getXDR)
      .exec(GetRemindersRequest.getRemindersRequest)
      exec(fixtureDistribution)
     // .exec(epgReminderDistribution)
      .exec(PageDetailScreen.openTrayRecommendationRecosenseList)
      .exec(PageDetailScreen.openTrayRecommendationCatchMediaList)
    // .exec(userRecommendationLanding)
  }
}
