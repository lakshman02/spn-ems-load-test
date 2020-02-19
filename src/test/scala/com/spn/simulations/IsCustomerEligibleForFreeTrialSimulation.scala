package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.{IsCustomerEligibleForFreeTrialScenario, _}
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class IsCustomerEligibleForFreeTrialSimulation extends Simulation{

  private val isCustomerEligibleForFreeTrialExec = IsCustomerEligibleForFreeTrialScenario.checkCustomerEligibleForFreeTrial
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(isCustomerEligibleForFreeTrialExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}

