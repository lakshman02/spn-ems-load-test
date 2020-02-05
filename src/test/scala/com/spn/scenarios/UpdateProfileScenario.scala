package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.UpdateProfileRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object UpdateProfileScenario{

  val dataFeeder=csv("data/platform.csv").random
  val updateProfileDataFeeder=csv("data/updateProfileData.csv").random
  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val updateProfileScenario =scenario("Update Profile Scenario")
    .feed(dataFeeder)
    .feed(updateProfileDataFeeder)
    .feed(dateTimeFeeder)
    .exec(UpdateProfileRequest.updateProfile)
}
