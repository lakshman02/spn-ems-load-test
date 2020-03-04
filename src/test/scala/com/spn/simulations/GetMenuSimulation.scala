package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetMenuScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

//simulation
class GetMenuSimulation extends Simulation {
  private val getMenuExec = GetMenuScenario.getMenuScenario
 .inject(rampUsers(15) during (30))
//    .inject(
//      rampUsers(Config.rampUp) during(Config.duration)
//    )

  setUp(getMenuExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
