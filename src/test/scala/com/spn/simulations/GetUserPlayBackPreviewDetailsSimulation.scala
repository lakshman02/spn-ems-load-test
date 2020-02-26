package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetUserPlayBackPreviewDetailsScenario
import io.gatling.core.Predef.{Simulation, _}

class GetUserPlayBackPreviewDetailsSimulation extends Simulation {
  private val PreviewDetailsExec = GetUserPlayBackPreviewDetailsScenario.getUserPlayBackPreviewDetailsScenario
        .inject(constantUsersPerSec(1) during (1))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(PreviewDetailsExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
