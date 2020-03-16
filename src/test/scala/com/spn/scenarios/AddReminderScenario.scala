package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.AddReminderRequest
import io.gatling.core.Predef.{scenario, _}

object AddReminderScenario {

  val contentIdData = csv("data/contentID.csv").circular
  val matchIdData = csv("data/matchID.csv").circular


  val addReminderScenario = scenario("Add reminder Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_ADDReminder)
    .feed(contentIdData)
    .feed(matchIdData)
    .exec(AddReminderRequest.addReminder)
}