package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.SubscriptionOrderStatusDateScenario
import io.gatling.core.Predef.{Simulation, _}

class SubscriptionOrderStatusDateSimulation extends Simulation {
  private val SubscriptionOrderStatusDateScenariorExec = SubscriptionOrderStatusDateScenario.subscriptionOrderStatusDateScenario
    .inject(rampUsers(15) during (30))

  setUp(SubscriptionOrderStatusDateScenariorExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}