package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.LoggedInUserPlansAndSubscriptionScenario
import io.gatling.core.Predef.{Simulation, _}

class LoggedInUserPlansAndSubscriptionSimulation extends Simulation {

  private val plansandsubscriptionExec = LoggedInUserPlansAndSubscriptionScenario.scnPlanAndSubscription
    .inject(
      rampUsers(1) during(1)
    )

  setUp(plansandsubscriptionExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}


