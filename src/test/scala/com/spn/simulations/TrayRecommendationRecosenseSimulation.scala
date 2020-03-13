package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.{TrayRecommendationRecosenseScenario, _}
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class TrayRecommendationRecosenseSimulation extends Simulation{

  private val trayRecommendationRecosenseSimulationExec = TrayRecommendationRecosenseScenario.trayRecommendationRecosenseScenario
    .inject(
      rampUsers(5) during(5)
    )

  setUp(trayRecommendationRecosenseSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}



