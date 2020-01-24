package com.spn.scenarios

import com.spn.requests.AccountSearchRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

//Account Search Scenario
object AccountSearchScenario {
  val dataFeeder=csv("platform.csv").random
  val dataFeederOtpRequirements = csv("otp_requirements.csv").circular

  val accountSearchScenario =scenario("Account Search Scenario")
    .feed(dataFeeder)
    .feed(dataFeederOtpRequirements)
    .exec(AccountSearchRequest.accountSearch)
}
