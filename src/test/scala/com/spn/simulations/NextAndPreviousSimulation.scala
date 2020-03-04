package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.{NextAndPreviousScenario, _}
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class NextAndPreviousSimulation extends Simulation{

  private val nextAndPreviousSimulationExec = NextAndPreviousScenario.nextAndPreviousScenario
    .inject(
      rampUsers(15) during(30)
    )

  setUp(nextAndPreviousSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


