package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GenerateDeviceActivationCodeScenario
import io.gatling.core.Predef.{Simulation, _}

class GenerateDeviceActivationCodeSimulation extends Simulation {
  private val GenerateDeviceActivationCodeExec = GenerateDeviceActivationCodeScenario.generateDeviceActivationCodeScenario
        .inject(rampUsers(5) during (30))

  setUp(GenerateDeviceActivationCodeExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
