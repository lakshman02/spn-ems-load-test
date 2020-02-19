package com.spn.scenarios

import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

import com.spn.requests.PostSubscriptionHistoryRequest

object PostSubscriptionHistoryScenario {

private val feeder = csv("data/platform.csv")
  val bodydatafeeder = csv("data/LoginID.csv")

  val SubscriptionHistory = scenario("Post Subscription History")
    .feed(feeder)
    .feed(bodydatafeeder)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .exec(PostSubscriptionHistoryRequest.subscriptionHistory)
}
