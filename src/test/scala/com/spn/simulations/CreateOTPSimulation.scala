package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.CreateOTPScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

class CreateOTPSimulation extends Simulation{

  private val createOTPExec = CreateOTPScenario.createOTPScenario
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(createOTPExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}



