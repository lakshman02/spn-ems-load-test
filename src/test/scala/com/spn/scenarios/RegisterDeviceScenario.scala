package com.spn.scenarios

import com.spn.common.{ApiSecurity, AuthActivationCode, CommonFeedFiles, Constants}
import com.spn.requests.RegisterDeviceRequest
import com.spn.requests.GenerateDeviceActivationCodeRequest
import com.spn.scenarios.GenerateDeviceActivationCodeScenario.activationCode
import io.gatling.core.Predef.{exec, scenario}
import io.gatling.core.Predef._

object RegisterDeviceScenario{

  val registerDeviceScenario =scenario("Register Device Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_RegisterDevice)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .exec(ApiSecurity.getToken)
    .exec(GenerateDeviceActivationCodeRequest.generateDeviceActivationCode)
    .exec(RegisterDeviceRequest.registerDevice)
}
