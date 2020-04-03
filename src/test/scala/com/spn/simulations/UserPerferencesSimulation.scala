package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.UserPreferencesScenario
import io.gatling.core.Predef.{Simulation, _}

class UserPerferencesSimulation extends Simulation {
private val User_Perferences= UserPreferencesScenario.userPreferencesScenario
  .inject(rampUsers(Config.rampUp) during(Config.duration) )

  setUp(User_Perferences).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))

}
