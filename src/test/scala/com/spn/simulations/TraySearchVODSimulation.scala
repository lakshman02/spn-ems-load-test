package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.{TraySearchVODScenario, _}
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class TraySearchVODSimulation extends Simulation{

  private val traySearchVODSimulationExec = TraySearchVODScenario.traySearchVODScenario
    .inject(
      rampUsers(5) during(5)
    )

  setUp(traySearchVODSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}
