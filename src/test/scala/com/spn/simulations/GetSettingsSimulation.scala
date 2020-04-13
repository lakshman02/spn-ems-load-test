package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetSettingsScenario
import io.gatling.core.Predef.{Simulation, _}

class GetSettingsSimulation extends Simulation {
  val getSettingsExec = GetSettingsScenario.getSettingsScenario
    .inject(rampUsers(15) during (30))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(getSettingsExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}