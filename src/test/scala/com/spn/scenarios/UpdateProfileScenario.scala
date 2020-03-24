package com.spn.scenarios

import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.UpdateProfileRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

import scala.util.Random

object UpdateProfileScenario{

  val updateProfileDataFeeder=csv("data/profileUpdate.csv").circular
  val dateOfBirthFeeder = Iterator.continually(
    Map("dateOfBirth" -> ThreadLocalRandom.current().nextInt(1551081657,1582617662))
  )

  val updateProfileScenario =scenario("Update Profile Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(updateProfileDataFeeder)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(dateOfBirthFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .exec(ApiSecurity.getToken)
    .exec(UpdateProfileRequest.updateProfile)
}
