package com.spn.simulations.journeySimulation

import com.spn.config.Config
import com.spn.scenarios.journeyScenario.LoginWithEmailAndPwdJourneyScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

class LoginWithEmailAndPwdJourneySimulation extends Simulation{

  private val loginWithEmail = LoginWithEmailAndPwdJourneyScenario.loginWithEmailAndPWDJourneyScenario
    .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
    )

  setUp(loginWithEmail).protocols(Config.httpProtocol)
  /* .assertions(
     global.responseTime.max.lt(Config.maxResponseTime)
   )*/
}



