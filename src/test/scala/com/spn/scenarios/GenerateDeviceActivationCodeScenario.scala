package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.common.{CommonFeedFiles, Constants}
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
    .exec(session => {
      val printActivationCode = session(Constants.RESP_ACTIVATION_CODE).as[String]
      println("======================================================")
      println("activationCode: =====>> " + printActivationCode)
      println("======================================================")
      session})
}
