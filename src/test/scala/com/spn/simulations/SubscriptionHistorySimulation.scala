package com.spn.simulations

import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

import com.spn.scenarios.PostSubscriptionHistoryScenario
import com.spn.config.Config

class SubscriptionHistorySimulation extends Simulation{
private val SubscriptionHistory = PostSubscriptionHistoryScenario.SubscriptionHistory
  .inject(rampUsers(Config.rampUp) during(Config.duration))

  setUp(SubscriptionHistory).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))
}
