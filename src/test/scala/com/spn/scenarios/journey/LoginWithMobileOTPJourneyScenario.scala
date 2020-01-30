package com.spn.scenarios.journey

import com.spn.requests._
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario


object LoginWithMobileOTPJourneyScenario {
  val dataFeeder=csv("data/platform.csv").random
  val loginData = csv("data/LoginID.csv").circular
  val otpData = csv("data/otp_requirements.csv").circular

  val loginWithMobileOTPJourneyScenario = scenario("Login with Mobile & OTP Scenario")
    .feed(dataFeeder)
    .feed(loginData)
    .feed(otpData)
    .exec(
    GetInitialConfigRequest.getInitialConfig,
    GetMenuRequest.getMenu,
    GetPageIdRequest.PageId,
      LoginRequest.LoginRequest,
      CreateOTPRequest.createOTPRequest
    )
}
