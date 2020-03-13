package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.EpgReminderGetListScenario
import io.gatling.core.Predef.{Simulation, _}

class EpgReminderGetListSimulation extends Simulation {
  private val EpgReminderGetList = EpgReminderGetListScenario.scnEpg_reminder_GetList
    .inject(rampUsers(1) during (1))


  setUp(EpgReminderGetList).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}