package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GenerateDeviceActivationCodeScenario
import io.gatling.core.Predef.{Simulation, _}

class GenerateDeviceActivationCodeSimulation extends Simulation {
  private val GenerateDeviceActivationCodeExec = GenerateDeviceActivationCodeScenario.generateDeviceActivationCodeScenario
        .inject(rampUsers(15) during (30))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(GenerateDeviceActivationCodeExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
