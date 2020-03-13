package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.AddSettingsScenario
import io.gatling.core.Predef.{Simulation, _}

class AddSettingsSimulation extends Simulation {
  private val addSettingsExec = AddSettingsScenario.addSettingsScenario
    .inject(rampUsers(15) during (30))
    setUp(addSettingsExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
