package com.spn.poc

import com.spn.config.Config
import io.gatling.core.Predef.{Simulation, _}

class GuestUserAppLaunchPOCSimulation extends Simulation {
  private val guestUserAppLaunchExec = GuestUserAppLaunchScenarioPOC.guestUserAppLaunchScenario
    .inject(
      rampUsers(20) during(10)
    )

  setUp(guestUserAppLaunchExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}


