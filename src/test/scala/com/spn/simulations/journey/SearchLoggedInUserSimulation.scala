package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.SearchFunctionalityForUserScenario
import io.gatling.core.Predef.{Simulation, _}

class SearchLoggedInUserSimulation extends Simulation {
  private val searchExec = SearchFunctionalityForUserScenario.doSearchScenario
    .inject(
      rampUsers(50) during (50)
    )

  setUp(searchExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}