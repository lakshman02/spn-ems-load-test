package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.LoginScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

  class LoginSimulation extends Simulation {

  private val loginSimulationExec = LoginScenario.loginScenario
    //.inject(constantUsersPerSec(Config.users) during (Config.duration seconds))
    .inject(
      rampUsers(100) during(300)
    )

  setUp(loginSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )
}
