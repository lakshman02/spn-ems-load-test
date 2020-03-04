package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetTokenScenario
import io.gatling.core.Predef.{Simulation, _}

class GetTokenSimulation extends Simulation {
  private val getTokenExec = GetTokenScenario.getTokenScenario
    .inject(rampUsers(15) during (30))
  //    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(getTokenExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}