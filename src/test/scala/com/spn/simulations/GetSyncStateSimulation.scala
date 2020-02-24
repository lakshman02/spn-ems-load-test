package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetSyncStateScenario
import io.gatling.core.Predef.{Simulation, _}

//simulation
class GetSyncStateSimulation extends Simulation {
  private val getSyncStateExec = GetSyncStateScenario.getSyncStateScenario
//    .inject(constantUsersPerSec(Config.users) during (Config.duration))
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(getSyncStateExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
