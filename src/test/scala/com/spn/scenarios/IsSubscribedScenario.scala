package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.IsSubscribedRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object IsSubscribedScenario{

  val dataFeeder=csv("data/platform.csv").random
  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val isSubscribedScenario =scenario("Is Subscribed Scenario")
    .feed(dataFeeder)
    .feed(dateTimeFeeder)
    .exec(IsSubscribedRequest.isSubscribed)
}
