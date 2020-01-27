package com.spn.simulations

import io.gatling.core.Predef.Simulation
import com.spn.config.Config
import com.spn.scenarios.GetPageIdScenario
import io.gatling.core.Predef._

class GetPageId extends Simulation
{
private val getPageId = GetPageIdScenario.scnLandingPage
  .inject(incrementUsersPerSec(Config.users)
    .times(Config.times)
    .eachLevelLasting(Config.eachLevelLasting)
    .separatedByRampsLasting(Config.separatedByRampsLasting)
    .startingFrom(Config.startingFrom)
  )


setUp(getPageId).protocols(Config.httpProtocol)
    .assertions(/*global.responseTime.max.lt(Config.defaultResponseTime),*/
        global.failedRequests.count.is(0)
    )
}
