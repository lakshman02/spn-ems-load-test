package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.MyListJourneyScenario
import io.gatling.core.Predef.{Simulation, _}

class MyListJourneySimulation extends Simulation {
  private val myListExec = MyListJourneyScenario.myListScenario
    .inject(
      rampUsers(1) during(1)
    )

  setUp(myListExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}