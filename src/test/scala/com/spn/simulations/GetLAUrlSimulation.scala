package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetLAUrlScenario
import io.gatling.core.Predef.{Simulation, _}

class GetLAUrlSimulation extends Simulation{

  private val getLAUrlExec = GetLAUrlScenario.getLAUrlScenario
    .inject(rampUsers(1) during(1))

  setUp(getLAUrlExec).protocols(Config.httpProtocol)
    .assertions(/*global.responseTime.max.lt(Config.defaultResponseTime),*/
      global.failedRequests.count.is(0)
    )

}
