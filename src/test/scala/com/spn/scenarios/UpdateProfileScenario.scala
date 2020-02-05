package com.spn.scenarios

import com.spn.requests.UpdateProfileRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object UpdateProfileScenario{
  val dataFeeder=csv("data/email.csv").random
  val updateProfileScenario =scenario("Update Profile Scenario")
    .feed(dataFeeder)
    .exec(UpdateProfileRequest.updateProfile)
}
