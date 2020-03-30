package com.spn.scenarios.groups


import java.time.LocalDateTime

import com.spn.common.Constants
import com.spn.requests.{EpgReminderGetListRequest, GetPageIdRequest}
import com.spn.scenarios.groups.UserAppLaunchScenario.setRandomPageURLToSession
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

  val guestUserHomeScreenScenario = doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(openHomePage)
    .exec(openEpgList)
  }
}
