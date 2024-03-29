package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.GetDRMDeviceIdRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object GetDRMDeviceIdScenario {

  val deviceFeeder = csv("data/device_details.csv").circular

  val getDRMDeviceIdScenario = scenario("Get DRMDevice ID Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(deviceFeeder)
    .exec(ApiSecurity.getToken)
    .exec(GetDRMDeviceIdRequest.getDRMDeviceIdRequest)
}


