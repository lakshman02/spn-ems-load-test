package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.common.CommonFeedFiles
import com.spn.requests.GenerateDeviceActivationCodeRequest
import io.gatling.core.Predef.{scenario, _}

object GenerateDeviceActivationCodeScenario{

  val generateDeviceActivationCodeScenario =scenario("Generate Device Activation Code Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.inputStagingDataFeeder)

    .exec(GenerateDeviceActivationCodeRequest.generateDeviceActivationCode)
}
