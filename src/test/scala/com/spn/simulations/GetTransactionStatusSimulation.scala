package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetTransactionStatusScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._


class GetTransactionStatusSimulation extends Simulation
{
private val getTransactionStatus = GetTransactionStatusScenario.getTransactionStatusScenario
  .inject(incrementUsersPerSec(Config.users)
    .times(Config.times)
    .eachLevelLasting(Config.eachLevelLasting)
    .separatedByRampsLasting(Config.separatedByRampsLasting)
    .startingFrom(Config.startingFrom)
  )


setUp(getTransactionStatus).protocols(Config.httpProtocol)
    .assertions(
        global.failedRequests.count.is(0)
    )
}
