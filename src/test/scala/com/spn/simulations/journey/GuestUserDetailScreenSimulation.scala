package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.GuestUserDetailScreenScenario
import io.gatling.core.Predef.{Simulation, _}

class GuestUserDetailScreenSimulation extends Simulation {

  private val guestUserDetailScreenExec = GuestUserDetailScreenScenario.guestUserDetailScreenScenario
    .inject(
      rampUsers(10) during(10)
    )

  setUp(guestUserDetailScreenExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}


