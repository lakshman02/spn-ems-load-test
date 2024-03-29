package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.AddListScenario
import io.gatling.core.Predef.{Simulation, _}

class AddListSimulation extends Simulation {
  private val addListExec = AddListScenario.addListScenario
    .inject(rampUsers(15) during (30))
  //    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(addListExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}