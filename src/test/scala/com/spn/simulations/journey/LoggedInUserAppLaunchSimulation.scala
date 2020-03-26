package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.GuestUserAppLaunchScenario
import io.gatling.core.Predef.{Simulation, _}

class LoggedInUserAppLaunchSimulation extends Simulation {
  private val guestUserAppLaunchExec = GuestUserAppLaunchScenario.guestUserAppLaunchScenario
    .inject(
      rampUsers(5) during(10)
    )

  setUp(guestUserAppLaunchExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}


