package com.spn.simulations

import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

import com.spn.scenarios.ActiveSubscriptionScenario
import com.spn.config.Config
class ActiveSubscriptionSimulation extends Simulation{

  private val ActiveSubscription = ActiveSubscriptionScenario.activeSubscriptionScenario
    .inject(rampUsers(Config.rampUp) during(Config.duration))

  setUp(ActiveSubscription).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))

}
