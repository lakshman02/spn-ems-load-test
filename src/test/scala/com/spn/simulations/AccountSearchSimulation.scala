package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.AccountSearchScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

class AccountSearchSimulation extends Simulation {
  private val AccountSearchExec = AccountSearchScenario.accountSearchScenario
    //    .inject(constantUsersPerSec(Config.users) during (Config.duration))
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(AccountSearchExec).protocols(Config.httpProtocol)
    .assertions(
      global.responseTime.max.lt(Config.maxResponseTime)
    )
}
