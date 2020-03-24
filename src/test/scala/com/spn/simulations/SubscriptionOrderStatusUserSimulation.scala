package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.SubscriptionOrderStatusUserScenario
import io.gatling.core.Predef.{Simulation, _}

class SubscriptionOrderStatusUserSimulation extends Simulation {
  private val SubscriptionOrderStatusUserScenariorExec = SubscriptionOrderStatusUserScenario.subscriptionOrderStatusUserScenario
    .inject(rampUsers(15) during (30))

  setUp(SubscriptionOrderStatusUserScenariorExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}