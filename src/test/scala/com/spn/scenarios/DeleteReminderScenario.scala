package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.DeleteReminderRequest
import io.gatling.core.Predef.{scenario, _}

object DeleteReminderScenario {

  val dataFeederContentId = csv("data/contentID.csv").circular

  val deleteReminderScenario = scenario("Delete Reminder Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_DeleteReminder)
    .feed(dataFeederContentId)
    .exec(DeleteReminderRequest.deleteReminderRequest)

}
