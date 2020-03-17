package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.CreateOTPRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object CreateOTPScenario {

  val createOTPScenario = scenario("Create OTP Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .exec(CreateOTPRequest.createOTPRequest)

}
