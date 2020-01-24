package com.spn.scenarios

import com.spn.requests.GetProfileRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object GetProfileScenario {
  val dataFeeder=csv("platform.csv").random
  val getProfileScenario =scenario("Get Profile Scenario")
    .feed(dataFeeder)
    .exec(GetProfileRequest.getProfile)
}
