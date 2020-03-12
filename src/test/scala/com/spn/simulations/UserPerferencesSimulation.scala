package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.UserPerferencesScenario
import io.gatling.core.Predef.{Simulation, _}

class UserPerferencesSimulation extends Simulation {
private val User_Perferences= UserPerferencesScenario.scnUserPerferences
  .inject(rampUsers(Config.rampUp) during(Config.duration) )

  setUp(User_Perferences).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))

}
