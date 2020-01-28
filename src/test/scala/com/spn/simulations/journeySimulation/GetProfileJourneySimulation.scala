package com.spn.simulations.journeySimulation

import com.spn.config.Config
import com.spn.scenarios.journeyScenario.GetProfileJourneyScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

class GetProfileJourneySimulation extends Simulation {
  private val getProfileJourneyExec = GetProfileJourneyScenario.getProfileJourneyScenario
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(getProfileJourneyExec).protocols(Config.httpProtocol)
    .assertions(
      global.responseTime.max.lt(Config.maxResponseTime)
    )
}
