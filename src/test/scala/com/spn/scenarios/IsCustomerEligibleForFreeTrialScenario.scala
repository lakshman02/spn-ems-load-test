package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.IsCustomerEligibleForFreeTrialRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object IsCustomerEligibleForFreeTrialScenario {

  val dataFeeder = csv("data/platform.csv").random
  val authFeeder = csv("data/LoginID.csv").random

  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val checkCustomerEligibleForFreeTrial = scenario("Check if the user is eligible for free trial Scenario")
    .feed(dataFeeder)
    .feed(dateTimeFeeder)
    .feed(authFeeder)
    .exec(IsCustomerEligibleForFreeTrialRequest.checkCustomerEligibleForFreeTrial)

}
