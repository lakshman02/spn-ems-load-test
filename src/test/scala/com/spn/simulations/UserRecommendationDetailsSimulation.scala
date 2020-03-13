package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.{UserRecommendationDetailsScenario, _}
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class UserRecommendationDetailsSimulation extends Simulation{

  private val userRecommendationDetailsSimulationExec = UserRecommendationDetailsScenario.userRecommendationDetailsScenario
    .inject(
      rampUsers(5) during(5)
    )

  setUp(userRecommendationDetailsSimulationExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}

