package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.AddXdrScenario
import io.gatling.core.Predef.{Simulation, _}

class AddXDRSimulation extends Simulation {
  private val addXdrExec = AddXdrScenario.addXdrScenario
    .inject(rampUsers(15) during (30))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(addXdrExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}