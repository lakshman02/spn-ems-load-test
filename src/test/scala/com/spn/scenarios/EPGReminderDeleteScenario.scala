package com.spn.scenarios

import com.redis.E
import com.spn.common.CommonFeedFiles
import com.spn.requests.EpgReminderRequest
import com.spn.requests.EpgReminderDeleteRequest
import io.gatling.core.Predef.scenario

object EPGReminderDeleteScenario{

  val EPGReminderDeleteScenario =scenario("EPG Reminder Delete Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_DeleteReminder)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .exec(EpgReminderDeleteRequest.epgReminderDelete)
}
