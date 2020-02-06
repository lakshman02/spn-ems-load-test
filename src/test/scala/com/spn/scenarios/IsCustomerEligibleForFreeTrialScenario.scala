package com.spn.scenarios

import com.spn.requests.IsCustomerEligibleForFreeTrialRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object IsCustomerEligibleForFreeTrialScenario {

  val dataFeeder = csv("data/platform.csv").random

  val checkCustomerEligibleForFreeTrial = scenario("Check if the user is eligible for free trial Scenario")
    .feed(dataFeeder)
    .exec(IsCustomerEligibleForFreeTrialRequest.checkCustomerEligibleForFreeTrial)

}
