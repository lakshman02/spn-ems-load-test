package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.RegisterDeviceRequest
import io.gatling.core.Predef.scenario
import com.spn.common.Constants

object RegisterDeviceScenario{

  val registerDeviceScenario =scenario("Register Device Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_RegisterDevice)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .exec(RegisterDeviceRequest.registerDevice)
}
