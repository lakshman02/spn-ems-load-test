package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetULDScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class GetULDSimulation extends Simulation{

  private val getULDExec = GetULDScenario.getULDScenario
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(getULDExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}
