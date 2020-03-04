package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.{SearchDescriptionScenario, _}
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class SearchDescriptionSimulation extends Simulation{

  private val searchDescriptionSimulationExec = SearchDescriptionScenario.searchDescriptionScenario
    .inject(
      rampUsers(15) during(30)
    )

  setUp(searchDescriptionSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


