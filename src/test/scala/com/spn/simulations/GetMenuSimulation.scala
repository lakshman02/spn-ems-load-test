package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetMenuScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

//simulation
class GetMenuSimulation extends Simulation {
  private val getMenuExec = GetMenuScenario.getMenuScenario
//    .inject(constantUsersPerSec(Config.users) during (Config.duration))
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(getMenuExec).protocols(Config.httpProtocol)
    .assertions(
      global.responseTime.max.lt(Config.maxResponseTime)
    )
}
