package com.spn.scenarios

import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

import com.spn.requests.PostSubscriptionHistoryRequest

object PostSubscriptionHistoryScenario {

private val feeder = csv("data/platform.csv")

  val SubscriptionHistory = scenario("Post Subscription History")
    .feed(feeder)
    .exec(PostSubscriptionHistoryRequest.subscriptionHistory)
}
