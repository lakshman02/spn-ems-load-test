package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.UpgradablePlansScenario
import io.gatling.core.Predef.{Simulation, _}

class UpgradablePlansSimulation extends Simulation {
  private val upgradablePlansSimulationExec = UpgradablePlansScenario.upgradablePlansScenario
    .inject(constantUsersPerSec(2) during (1))
  //    .inject(
  //      incrementUsersPerSec(Config.users)
  //        .times(Config.times)
  //        .eachLevelLasting(Config.eachLevelLasting)
  //        .separatedByRampsLasting(Config.separatedByRampsLasting)
  //        .startingFrom(Config.startingFrom)
  //    )

  setUp(upgradablePlansSimulationExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}