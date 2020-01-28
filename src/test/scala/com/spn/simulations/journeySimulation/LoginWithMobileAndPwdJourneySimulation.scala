package com.spn.simulations.journeySimulation

import com.spn.config.Config
import com.spn.scenarios.journeyScenario.LoginWithMobileAndPwdJourneyScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

class LoginWithMobileAndPwdJourneySimulation extends Simulation {
  private val LoginWithMobileAndPwdJourneyExec = LoginWithMobileAndPwdJourneyScenario.loginWithMobileAndPwdJourney
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(LoginWithMobileAndPwdJourneyExec).protocols(Config.httpProtocol)
  /* .assertions(
     global.responseTime.max.lt(Config.maxResponseTime)
   )*/
}
