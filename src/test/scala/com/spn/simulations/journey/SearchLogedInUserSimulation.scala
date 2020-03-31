package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.SearchLoggedInUserScenario
import io.gatling.core.Predef.{Simulation, _}

class SearchLogedInUserSimulation extends Simulation {
  private val loginExec = SearchLoggedInUserScenario.SearchLoggedInUserScenario
    .inject(
      rampUsers(10) during(10)
    )

  setUp(loginExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}