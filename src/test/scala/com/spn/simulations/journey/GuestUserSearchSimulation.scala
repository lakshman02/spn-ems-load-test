package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.GuestUserSearchScenario
import io.gatling.core.Predef.{Simulation, _}

class GuestUserSearchSimulation extends Simulation {
  private val searchExec = GuestUserSearchScenario.guestSearch_scn
    .inject(
      rampUsers(50) during(50)
    )

  setUp(searchExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}