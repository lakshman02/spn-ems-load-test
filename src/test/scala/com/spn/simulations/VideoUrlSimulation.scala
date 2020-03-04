package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.VideoUrlScenario
import io.gatling.core.Predef.{Simulation, _}

class VideoUrlSimulation extends Simulation {
  private val videoUrlExec = VideoUrlScenario.videoUrlScenario
    .inject(rampUsers(15) during (30))
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
