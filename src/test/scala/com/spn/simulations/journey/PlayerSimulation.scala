package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.PlayerScenario
import io.gatling.core.Predef.{Simulation, _}

class PlayerSimulation extends Simulation {
  private val playerExec = PlayerScenario.doPlayerOperationsScenario
    .inject(
      rampUsers(50) during(50)
    )

  setUp(playerExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}