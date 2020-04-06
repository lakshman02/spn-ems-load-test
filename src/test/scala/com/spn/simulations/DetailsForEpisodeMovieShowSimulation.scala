package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.DetailsForEpisodeScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

class DetailsForEpisodeMovieShowSimulation extends Simulation{

  private val DetailsForEpisodeMovieShowExec = DetailsForEpisodeScenario.detailsForEpisodeScenario
    .inject(
      rampUsers(15) during(30)
    )

  setUp(DetailsForEpisodeMovieShowExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


