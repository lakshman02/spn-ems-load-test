package com.spn.simulations

import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

import com.spn.scenarios.SubscriptionRemoveScenario
import com.spn.config.Config



class SubscriptionRemoveSimulation extends Simulation {
  private val subscriptionRemoveExec = SubscriptionRemoveScenario.subscriptionRemoveScenario
    .inject(constantUsersPerSec(1) during (1))
  //.inject(rampUsers(Config.rampUp) during(Config.duration))

  setUp(subscriptionRemoveExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
