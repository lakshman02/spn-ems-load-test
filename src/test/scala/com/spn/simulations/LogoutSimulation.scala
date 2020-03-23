package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.LogoutScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class LogoutSimulation extends Simulation{

  private val LogoutScenarioExec = LogoutScenario.logoutScenario
    .inject(
      rampUsers(15) during(30)
    )

  setUp(LogoutScenarioExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}

