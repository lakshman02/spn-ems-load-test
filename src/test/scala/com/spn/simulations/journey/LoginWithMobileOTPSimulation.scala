package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.LoginWithMobileOTPJourneyScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

class LoginWithMobileOTPSimulation extends Simulation {
  private val LoginWithMobileOTPSExec=LoginWithMobileOTPJourneyScenario.loginWithMobileOTPJourneyScenario
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(LoginWithMobileOTPSExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}
