package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.EpgReminderRequest
import io.gatling.core.Predef.{scenario, _}

object EPGReminderScenario{

  val EPGReminderScenario =scenario("EPG Reminder Save Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_ADDReminder)
    .feed(CommonFeedFiles.inputStagingDataFeeder)

    .exec(EpgReminderRequest.epgReminder)
}
