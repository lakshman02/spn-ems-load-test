package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.EPGReminderDeleteScenario
import io.gatling.core.Predef.{Simulation, _}

class EPGReminderDeleteSimulation extends Simulation {
  private val EPGReminderExec = EPGReminderDeleteScenario.EPGReminderDeleteScenario
    .inject(rampUsers(15) during (30))
  //    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(EPGReminderExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}