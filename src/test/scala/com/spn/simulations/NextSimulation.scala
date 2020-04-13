package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios._
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class NextSimulation extends Simulation{

  private val NextSimulationExec = NextScenario.nextScenario
    .inject(
      rampUsers(15) during(30)
    )

  setUp(NextSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


