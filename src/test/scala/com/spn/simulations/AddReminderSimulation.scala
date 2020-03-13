package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.AddReminderScenario
import io.gatling.core.Predef.{Simulation, _}

class AddReminderSimulation extends Simulation {
  private val addReminderExec = AddReminderScenario.addReminderScenario
    .inject(rampUsers(15) during (15))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(addReminderExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}