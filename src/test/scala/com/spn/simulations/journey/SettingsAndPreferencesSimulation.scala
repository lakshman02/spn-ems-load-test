package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.SettingsAndPreferenceScenario
import io.gatling.core.Predef.{Simulation, _}

class SettingsAndPreferencesSimulation extends Simulation {
  private val settingsAndPreferenceExec = SettingsAndPreferenceScenario.doSettingsAndPreferenceOperationsScenario
    .inject(
      rampUsers(50) during(50)
    )

  setUp(settingsAndPreferenceExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}