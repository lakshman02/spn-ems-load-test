package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.MovieDetailScenario
import io.gatling.core.Predef.{Simulation, _}


class movieDetailSimulation extends Simulation {
  private val movieDetailExec = MovieDetailScenario.movieDetailScenario
        .inject(constantUsersPerSec(1) during (1))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(movieDetailExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
