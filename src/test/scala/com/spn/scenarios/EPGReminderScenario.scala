package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.EpgReminderRequest
import io.gatling.core.Predef.{scenario, _}

object EPGReminderScenario{

  val EPGReminderScenario =scenario("EPG Reminder Save Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_ADDEpgReminder)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .exec(ApiSecurity.getToken)
    .exec(EpgReminderRequest.epgReminder)
}
