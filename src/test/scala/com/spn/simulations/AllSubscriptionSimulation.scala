package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios._
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class AllSubscriptionSimulation extends Simulation{

  private val allSubscriptionsScenarioExec = AllSubscriptionsScenario.getAllSubscriptionsScenario
    .inject(
      rampUsers(15) during(30)
    )

  setUp(allSubscriptionsScenarioExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}

