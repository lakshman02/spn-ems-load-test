package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.{TrayRecommendationCatchMediaScenario, _}
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class TrayRecommendationCatchMediaSimulation extends Simulation{

  private val trayRecommendationCatchMediaSimulationExec = TrayRecommendationCatchMediaScenario.trayRecommendationCatchMediaScenario
    .inject(
      rampUsers(15) during(30)
    )

  setUp(trayRecommendationCatchMediaSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}

