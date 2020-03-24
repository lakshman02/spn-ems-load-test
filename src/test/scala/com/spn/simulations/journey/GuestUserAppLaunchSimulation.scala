package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.poc.GuestUserAppLaunchScenarioPOC
import com.spn.scenarios.journey.GuestUserAppLaunchScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

class GuestUserAppLaunchSimulation extends Simulation {
  private val guestUserAppLaunchExec = GuestUserAppLaunchScenario.guestUserAppLaunchScenario
    .inject(
      rampUsers(20) during(10)
    )

  setUp(guestUserAppLaunchExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}


