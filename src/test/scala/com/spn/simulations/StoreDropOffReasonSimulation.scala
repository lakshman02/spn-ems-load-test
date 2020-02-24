package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.StoreDropOffReasonScenario
import io.gatling.core.Predef.{Simulation, _}

class StoreDropOffReasonSimulation extends Simulation {
  private val storeDropOffReasonExec = StoreDropOffReasonScenario.storeDropOffReasonScenario
        .inject(constantUsersPerSec(1) during (1))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(storeDropOffReasonExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
