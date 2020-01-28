package com.spn.simulations.journeySimulation

import com.spn.config.Config
import com.spn.scenarios.journeyScenario.LoginWithMobileOTPJourneyScenario
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
   /* .assertions(
      global.responseTime.max.lt(Config.maxResponseTime)
    )*/
}
