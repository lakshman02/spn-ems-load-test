package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.{IsSubscribedScenario, SubscriptionRemoveScenario}
import io.gatling.core.Predef.{Simulation, _}


//Account Search Simulation
class SubscriptionRemoveSimulation extends Simulation {
  private val subscriptionRemoveExec = SubscriptionRemoveScenario.subscriptionRemoveScenario
    .inject(rampUsers(Config.rampUp) during(Config.duration))
//    .inject(
//      incrementUsersPerSec(Config.users)
//        .times(Config.times)
//        .eachLevelLasting(Config.eachLevelLasting)
//        .separatedByRampsLasting(Config.separatedByRampsLasting)
//        .startingFrom(Config.startingFrom)
//    )

  setUp(subscriptionRemoveExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
