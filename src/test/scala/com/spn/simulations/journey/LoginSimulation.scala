package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.LoginScenario
import io.gatling.core.Predef.{Simulation, _}

class LoginSimulation extends Simulation {
  private val loginExec = LoginScenario.loginScenario
    .inject(
      rampUsers(1) during(1)
    )

  setUp(loginExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}


