package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.complete.SonyLivCompleteUserJourney
import io.gatling.core.Predef.{Simulation, _}

class GuestUserDetailScreenSimulation extends Simulation {

  private val guestUserDetailScreenExec = SonyLivCompleteUserJourney.guestUserDetailScreenScenario
    .inject(
      rampUsers(20) during(10)
    )

  setUp(guestUserDetailScreenExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}

