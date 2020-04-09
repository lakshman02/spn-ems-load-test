package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetProfileScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

class GetProfileSimulation extends Simulation {
  private val getProfileExec = GetProfileScenario.getProfileScenario
    .inject(rampUsers(1) during (1))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(getProfileExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}