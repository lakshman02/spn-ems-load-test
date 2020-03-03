package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetDRMDeviceIdScenario
import io.gatling.core.Predef.{Simulation, _}

class GetDRMDeviceIdSimulation extends Simulation{

  private val getDRMDeviceIdExec = GetDRMDeviceIdScenario.getDRMDeviceIdScenario
    .inject(rampUsers(5) during(5))

  setUp(getDRMDeviceIdExec).protocols(Config.httpProtocol)
    .assertions(/*global.responseTime.max.lt(Config.defaultResponseTime),*/
      global.failedRequests.count.is(0)
    )
}

