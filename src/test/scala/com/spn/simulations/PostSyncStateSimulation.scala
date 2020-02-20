package com.spn.simulations

import com.spn.config.Config
import io.gatling.core.Predef._
import com.spn.scenarios.PostSyncStateScenario
import io.gatling.core.Predef.Simulation


class PostSyncStateSimulation extends Simulation {
  private val postSyncStateExec = PostSyncStateScenario.postSyncStateScenario
    .inject(constantUsersPerSec(1) during (1))
  //.inject(rampUsers(Config.rampUp) during(Config.duration))

  setUp(postSyncStateExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
