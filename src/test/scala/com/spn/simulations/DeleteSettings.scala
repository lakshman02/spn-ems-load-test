package com.spn.simulations

import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._
import com.spn.scenarios.DeleteSettingsScenario
import com.spn.config.Config

class DeleteSettings extends Simulation {
  private val DeleteSettingAPI = DeleteSettingsScenario.scnDeleteSetting
    .inject(rampUsers(Config.rampUp) during (Config.duration))

  setUp(DeleteSettingAPI).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))
}
