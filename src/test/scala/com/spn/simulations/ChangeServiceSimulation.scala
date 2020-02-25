package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.ChangeServiceScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

class ChangeServiceSimulation extends Simulation{

  private val changeServiceExec = ChangeServiceScenario.changeServiceScenario
    .inject(
      rampUsers(10) during(10)
    )

  setUp(changeServiceExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


