package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.LoginWithEmailScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

class LoginWithEmailSimulation extends Simulation {

  private val LoginWithEmailSimulationExec = LoginWithEmailScenario.LoginWithEmailScenario
    //.inject(constantUsersPerSec(Config.users) during (Config.duration seconds))
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(LoginWithEmailSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )
}