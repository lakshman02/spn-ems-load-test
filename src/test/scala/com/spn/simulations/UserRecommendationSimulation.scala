package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.UserRecommendationScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class UserRecommendationSimulation extends Simulation{

  private val userRecommendationSimulation = UserRecommendationScenario.userRecommendationScenario
    .inject(rampUsers(1) during(1))

  setUp(userRecommendationSimulation)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}

