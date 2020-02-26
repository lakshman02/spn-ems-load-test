package com.spn.scenarios

import java.time.LocalDateTime
import java.util.concurrent.ThreadLocalRandom

import com.spn.requests.UpdateProfileRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

import scala.util.Random

object UpdateProfileScenario{

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val updateProfileDataFeeder=csv("data/profileUpdate.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard

  val dateOfBirthFeeder = Iterator.continually(
    Map("dateOfBirth" -> ThreadLocalRandom.current().nextInt(1551081657,1582617662))
  )

  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val updateProfileScenario =scenario("Update Profile Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(updateProfileDataFeeder)
    .feed(dateTimeFeeder)
    .feed(dateOfBirthFeeder)
    .feed(userCredentials)
    .exec(UpdateProfileRequest.updateProfile)
}
