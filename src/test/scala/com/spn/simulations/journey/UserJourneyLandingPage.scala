package com.spn.simulations.journey

import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._
import com.spn.scenarios.journey.UserJourneyLandingPage
import com.spn.config.Config

class UserJourneyLandingPage extends Simulation{
private val LandingPage = UserJourneyLandingPage.LandingPage

  .inject(incrementUsersPerSec(Config.users)
    .times(Config.times)
    .eachLevelLasting(Config.eachLevelLasting)
    .separatedByRampsLasting(Config.separatedByRampsLasting)
    .startingFrom(Config.startingFrom)
  )

  setUp(LandingPage).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))

}
