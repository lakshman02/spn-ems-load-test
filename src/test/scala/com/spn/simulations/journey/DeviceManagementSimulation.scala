package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.DeviceManagementScenario
import io.gatling.core.Predef.{Simulation, _}

class DeviceManagementSimulation extends Simulation {
  private val deviceManagementExec = DeviceManagementScenario.DeviceManagementScenario
    .inject(
      rampUsers(50) during(50)
    )

  setUp(deviceManagementExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}