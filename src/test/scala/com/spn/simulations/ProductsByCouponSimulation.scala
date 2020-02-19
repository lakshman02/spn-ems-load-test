package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.ProductsByCouponScenario
import io.gatling.core.Predef.{Simulation, _}


//Account Search Simulation
class ProductsByCouponSimulation extends Simulation {
  private val productsByCouponExec = ProductsByCouponScenario.productsByCouponScenario
        .inject(constantUsersPerSec(1) during (1))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(productsByCouponExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
