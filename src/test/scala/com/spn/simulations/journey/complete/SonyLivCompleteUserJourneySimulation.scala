package com.spn.simulations.journey.complete

import com.spn.config.Config
import com.spn.scenarios.journey.complete.SonyLivCompleteUserJourney
import io.gatling.core.Predef.{Simulation, _}

class SonyLivCompleteUserJourneySimulation extends Simulation {

  private val guestUserDetailScreenExec = SonyLivCompleteUserJourney.doSonyLivCompleteUserJourney
    .inject(
      rampUsers(5) during(5)
    )

  setUp(guestUserDetailScreenExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}

