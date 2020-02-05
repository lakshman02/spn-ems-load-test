package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.UpdateProfileRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object UpdateProfileScenario{

  val dataFeeder=csv("data/platform.csv").random
  val email=csv("data/email.csv").random
  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val updateProfileScenario =scenario("Update Profile Scenario")
    .feed(dataFeeder)
    .feed(email)
    .feed(dateTimeFeeder)
    .exec(UpdateProfileRequest.updateProfile)
}
