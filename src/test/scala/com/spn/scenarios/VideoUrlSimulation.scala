package com.spn.scenarios

import com.spn.config.Config
import io.gatling.core.Predef.{Simulation, _}

class VideoUrlSimulation extends Simulation {
  private val videoUrlExec = VideoUrlScenario.videoUrlScenario
        .inject(constantUsersPerSec(2) during (1))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(videoUrlExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
