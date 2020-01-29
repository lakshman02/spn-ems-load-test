package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.AccountSearchScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

//Account Search Simulation
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
      global.failedRequests.count.is(0)
    )
}
