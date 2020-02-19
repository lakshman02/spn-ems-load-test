package com.spn.scenarios

import com.spn.requests.ActiveSubscription

import io.gatling.core.Predef.{scenario, _}
object ActiveSubscriptionScenario   {

  val dataFeeder=csv("data/platform.csv").circular
  val bodydatafeeder = csv("data/LoginID.csv")

  val activeSubscriptionScenario = scenario("Active Subscription Scenario")
    .feed(dataFeeder)
    .feed(bodydatafeeder)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .exec(ActiveSubscription.ActiveSubscription)
  //.exec (session => println(session) session)

}