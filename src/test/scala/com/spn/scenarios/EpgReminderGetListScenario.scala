package com.spn.scenarios

import io.gatling.core.Predef.{scenario, _}

import com.spn.common.CommonFeedFiles
import com.spn.requests.EpgReminderGetListRequest

object EpgReminderGetListScenario {
  val scnEpg_reminder_GetList = scenario("EPG Reminder GET LIST")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.userAuth1KUsersUsingCircular)
    .exec(EpgReminderGetListRequest.EPG_GetList)
}