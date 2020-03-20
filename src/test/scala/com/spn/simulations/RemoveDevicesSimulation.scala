package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios._
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class RemoveDevicesSimulation extends Simulation{

  private val removeDevicesSimulationExec = RemoveDevicesScenario.removeDevicesScenario
    .inject(
      rampUsers(5) during(10)
    )

  setUp(removeDevicesSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


