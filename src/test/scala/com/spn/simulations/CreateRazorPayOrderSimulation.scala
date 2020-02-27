package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.CreateRazorPayOrderScenario
import io.gatling.core.Predef.{Simulation, _}

class CreateRazorPayOrderSimulation extends Simulation {
  private val createRazorPayOrderExec = CreateRazorPayOrderScenario.createRazorPayOrderScenario
        .inject(constantUsersPerSec(1) during (1))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(createRazorPayOrderExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
