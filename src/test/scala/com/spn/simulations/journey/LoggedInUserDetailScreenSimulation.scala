package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.LoggedInUserDetailScreenScenario
import io.gatling.core.Predef.{Simulation, _}

class LoggedInUserDetailScreenSimulation extends Simulation {

  private val loggedInUserDetailScreenExec = LoggedInUserDetailScreenScenario.doLoggedInUserDetailScreenScenario
    .inject(
      rampUsers(50) during(50)
    )

  setUp(loggedInUserDetailScreenExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}


