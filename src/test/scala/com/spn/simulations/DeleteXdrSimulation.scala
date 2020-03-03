package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.DeleteXdrScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

class DeleteXdrSimulation extends Simulation{

  private val DeleteXdrExec = DeleteXdrScenario.deleteXdrScenario
    .inject(
      rampUsers(1) during(1)
    )

  setUp(DeleteXdrExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


