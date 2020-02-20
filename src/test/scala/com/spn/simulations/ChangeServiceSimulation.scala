package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.ChangeServiceScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

class ChangeServiceSimulation extends Simulation{

  private val changeServiceExec = ChangeServiceScenario.changeServiceScenario
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(changeServiceExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


