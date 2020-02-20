package com.spn.scenarios

import com.spn.config.Config
import com.spn.requests.SubscriptionRemoveRequest
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SubscriptionRemoveScenario {

  val dataFeeder=csv("data/platform.csv").circular
  val subscriptionRemoveScenario= scenario("Subscription Payment Scenario")
    .feed(dataFeeder)
    .feed(CreateOTPScenario.dateTimeFeeder)

    .exec(SubscriptionRemoveRequest.subscriptionRemove)
  //.exec (session => println(session) session)
}
