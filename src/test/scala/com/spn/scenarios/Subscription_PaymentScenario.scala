package com.spn.scenarios

import com.spn.requests.Subscription_PaymentURL

import io.gatling.core.Predef.{scenario, _}

object Subscription_PaymentScenario  {

  val dataFeeder=csv("data/platform.csv").circular

  val subscription_PaymentScenario= scenario("Subscription Payment Scenario")
    .feed(dataFeeder)

    .exec(Subscription_PaymentURL.Subscription_Payment)
  //.exec (session => println(session) session)

}