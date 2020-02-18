package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.PlaceOrderScenario
import io.gatling.core.Predef.{Simulation, _}


//Account Search Simulation
class placeOrderSimulation extends Simulation {
  private val placeOrderExec = PlaceOrderScenario.placeOrderScenario
        .inject(constantUsersPerSec(1) during (1))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(placeOrderExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
