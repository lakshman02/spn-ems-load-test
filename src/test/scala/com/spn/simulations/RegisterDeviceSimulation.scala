package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.RegisterDeviceScenario
import io.gatling.core.Predef.{Simulation, _}

class RegisterDeviceSimulation extends Simulation {
  private val RegisterDeviceExec = RegisterDeviceScenario.registerDeviceScenario
       .inject(rampUsers(15) during (30))

  setUp(RegisterDeviceExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
