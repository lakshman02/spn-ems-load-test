package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.CreateOTPRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object CreateOTPScenario {

  val dataFeeder = csv("platform.csv").circular
  val dataFeederOtpRequirements = csv("otp_requirements.csv").circular

  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val createOTPScenario = scenario("Create OTP Scenario")
    .feed(dataFeeder)
    .feed(dataFeederOtpRequirements)
    .feed(dateTimeFeeder)
    .exec(CreateOTPRequest.createOTPRequest)

}
