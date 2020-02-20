package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.UpdateProfileScenario
import io.gatling.core.Predef.{Simulation, _}

class UpdateProfileSimulation extends Simulation {
  private val updateProfileExec = UpdateProfileScenario.updateProfileScenario
        .inject(constantUsersPerSec(1) during (1))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(updateProfileExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
