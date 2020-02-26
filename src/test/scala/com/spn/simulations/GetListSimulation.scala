package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios._
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class GetListSimulation extends Simulation{

  private val getListSimulationExec = GetListScenario.getListScenario
    .inject(
      rampUsers(10) during(5)
    )

  setUp(getListSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


