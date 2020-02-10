package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.AllSubscriptionsRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object AllSubscriptionsScenario {

  val dataFeeder = csv("data/platform.csv").circular
  val dataFeederOtpRequirements = csv("data/otp_requirements.csv").circular


  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val getAllSubscriptionsScenario = scenario("All Subscriptions Scenario")
    .feed(dataFeeder)
    .feed(dataFeederOtpRequirements)
    .feed(dateTimeFeeder)
    .exec(AllSubscriptionsRequest.getAllSubscriptions)
}
