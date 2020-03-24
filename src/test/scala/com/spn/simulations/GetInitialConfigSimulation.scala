package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetInitialConfigScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

class GetInitialConfigSimulation extends Simulation {

  private val getInitialConfigExec = GetInitialConfigScenario.getInitialConfigScenario
    .inject(atOnceUsers(15))
    //.inject(constantUsersPerSec(Config.users) during (Config.duration seconds))
    /*.inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )*/

  setUp(getInitialConfigExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )
}