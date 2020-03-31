package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.LoggedInUserHomeScreenScenario
import io.gatling.core.Predef.{Simulation, _}

class LoggedInUserHomeScreenSimulation extends Simulation {

  private val loggedInUserHomeScreenExec = LoggedInUserHomeScreenScenario.loggedInUserHomeScreenScenario
    .inject(
      rampUsers(50) during(50)
    )

  setUp(loggedInUserHomeScreenExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}


