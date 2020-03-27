package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.LoggedInUserAppLaunchScenario
import io.gatling.core.Predef.{Simulation, _}

class LoggedInUserAppLaunchSimulation extends Simulation {
  private val loggedInUserAppLaunchExec = LoggedInUserAppLaunchScenario.loggedInUserAppLaunchScenario
    .inject(
      rampUsers(50) during(60)
    )

  setUp(loggedInUserAppLaunchExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}


