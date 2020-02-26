package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.{TraySearchScenario, _}
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class TraySearchSimulation extends Simulation{

  private val traySearchSimulationExec = TraySearchScenario.traySearchScenario
    .inject(
      rampUsers(5) during(5)
    )

  setUp(traySearchSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}

