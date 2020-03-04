package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.{GetPageIdScenario, GroupOfBundlesScenario}
import io.gatling.core.Predef.{Simulation, _}

class GroupOfBundlesSimulation extends Simulation
{
private val groupOfBundles = GroupOfBundlesScenario.groupOfBundlesScenario
  .inject(rampUsers(15) during (30))
//  .inject(incrementUsersPerSec(Config.users)
//    .times(Config.times)
//    .eachLevelLasting(Config.eachLevelLasting)
//    .separatedByRampsLasting(Config.separatedByRampsLasting)
//    .startingFrom(Config.startingFrom)
//  )

setUp(groupOfBundles).protocols(Config.httpProtocol)
    .assertions(/*global.responseTime.max.lt(Config.defaultResponseTime),*/
        global.failedRequests.count.is(0)
    )
}
