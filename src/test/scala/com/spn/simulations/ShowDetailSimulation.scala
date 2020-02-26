package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.ShowDetailScenario
import io.gatling.core.Predef.{Simulation, _}

class ShowDetailSimulation extends Simulation
{
private val showDetail = ShowDetailScenario.showDetailScenario
  .inject(incrementUsersPerSec(Config.users)
    .times(Config.times)
    .eachLevelLasting(Config.eachLevelLasting)
    .separatedByRampsLasting(Config.separatedByRampsLasting)
    .startingFrom(Config.startingFrom)
  )


setUp(showDetail).protocols(Config.httpProtocol)
    .assertions(/*global.responseTime.max.lt(Config.defaultResponseTime),*/
        global.failedRequests.count.is(0)
    )
}
