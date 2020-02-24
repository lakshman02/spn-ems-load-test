package com.spn.simulations

import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

import com.spn.scenarios.Subscription_PaymentScenario
import com.spn.config.Config

class SubscriptionPaymentSimulation extends Simulation{
  private val SubscriptionPaymentExec = Subscription_PaymentScenario.subscription_PaymentScenario
    .inject(rampUsers(Config.rampUp) during(Config.duration))

  setUp(SubscriptionPaymentExec).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))
}


