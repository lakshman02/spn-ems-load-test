package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.GetLAUrlRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object GetLAUrlScenario {

  val deviceFeeder = csv("data/device_details.csv").circular

  val getLAUrlScenario = scenario("Get LA URL Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(deviceFeeder)
    .exec(GetLAUrlRequest.getLaUrl)
}



