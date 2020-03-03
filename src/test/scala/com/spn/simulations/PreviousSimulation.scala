package com.spn.simulations
import io.gatling.core.Predef._
import io.gatling.core.Predef.Simulation

import com.spn.config.Config
import com.spn.scenarios.PreviousScenario

class PreviousSimulation extends Simulation{
  private val contentPrevious = PreviousScenario.scnPreviousContent
    .inject(rampUsers(Config.rampUp)during(Config.duration))

  setUp(contentPrevious).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))

}
