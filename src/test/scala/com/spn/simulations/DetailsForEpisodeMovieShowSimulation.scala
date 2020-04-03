package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.DetailsForEpisodeMovieShowScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

class DetailsForEpisodeMovieShowSimulation extends Simulation{

  private val DetailsForEpisodeMovieShowExec = DetailsForEpisodeMovieShowScenario.detailsForEpisodeMovieShowScenario
    .inject(
      rampUsers(1) during(1)
    )

  setUp(DetailsForEpisodeMovieShowExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


