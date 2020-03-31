package com.spn.scenarios
import java.time.LocalDateTime
import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.GenerateDeviceActivationCodeRequest
import io.gatling.commons.validation.Validation
import io.gatling.core.Predef.{scenario, _}
import io.gatling.core.Predef._
import io.gatling.core.session

object GenerateDeviceActivationCodeScenario{
  var activationCode=""
  val generateDeviceActivationCodeScenario =scenario("Generate Device Activation Code Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .exec(ApiSecurity.getToken)
    .exec(GenerateDeviceActivationCodeRequest.generateDeviceActivationCode)
}