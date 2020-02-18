package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.IsSubscribedScenario
import io.gatling.core.Predef.{Simulation, _}


//Account Search Simulation
class IsSubscribedSimulation extends Simulation {
  private val isSubscribedExec = IsSubscribedScenario.isSubscribedScenario
        .inject(constantUsersPerSec(1) during (1))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(isSubscribedExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
