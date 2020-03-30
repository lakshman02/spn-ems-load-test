package com.spn.scenarios.groups


import java.time.LocalDateTime

import com.spn.common.Constants
import com.spn.requests.EpgReminderGetListRequest
import io.gatling.core.Predef._

object HomeScreen {

  val openEpgList = exec(session => {
    session.set("channelId", "ALL")
      .set("offset","100")
      .set("getDateTime", s"${LocalDateTime.now()}")
      .set("from","0")
      .set("size", "10")
  }).exec(EpgReminderGetListRequest.EPG_GetList)


  val guestUserHomeScreenScenario = doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(openEpgList)
  }
}
