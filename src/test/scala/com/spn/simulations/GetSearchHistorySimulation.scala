package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetSearchHistoryScenario
import io.gatling.core.Predef.{Simulation, _}

class GetSearchHistorySimulation extends Simulation{
private val GetSearchHistory = GetSearchHistoryScenario.getSearchHistoryScenario
  .inject(rampUsers(Config.rampUp) during(Config.duration))

  setUp(GetSearchHistory).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))
}
