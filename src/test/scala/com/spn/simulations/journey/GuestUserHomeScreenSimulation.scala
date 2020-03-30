package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.GuestUserHomeScreenScenario
import io.gatling.core.Predef.{Simulation, _}

class GuestUserHomeScreenSimulation extends Simulation {

  private val guestUserHomeScreenExec = GuestUserHomeScreenScenario.guestUserHomeScreenScenario
    .inject(
      rampUsers(1) during(1)
    )

  setUp(guestUserHomeScreenExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}


