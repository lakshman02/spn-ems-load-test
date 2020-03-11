package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.UserRecommendationLandingScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}


class UserRecommendationLandingSimulation extends Simulation{

  private val userRecommendationLandingsSimulation = UserRecommendationLandingScenario.scnuserRecommendationLandingScenario
    .inject(
      rampUsers(14) during(5)
    )

  setUp(userRecommendationLandingsSimulation)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}

