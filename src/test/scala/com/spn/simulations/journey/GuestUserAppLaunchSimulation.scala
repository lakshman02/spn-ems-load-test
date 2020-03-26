package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.poc.GuestUserAppLaunchScenarioPOC
import com.spn.scenarios.journey.GuestUserAppLaunchScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

class GuestUserAppLaunchSimulation extends Simulation {
  private val guestUserAppLaunchExec = GuestUserAppLaunchScenario.guestUserAppLaunchScenario
    .inject(
      rampUsersPerSec(20) to(1) during(60)
//      rampConcurrentUsers(20) to(1) during(60)
    )

  setUp(guestUserAppLaunchExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    ).maxDuration(60)
}


