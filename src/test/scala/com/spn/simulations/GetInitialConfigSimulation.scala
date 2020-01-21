package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetInitialConfigScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}
import io.gatling.http.Predef.http

class GetInitialConfigSimulation extends Simulation {

  val httpProtocol = http
    .acceptHeader("")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  private val getInitialConfigExec = GetInitialConfigScenario.getInitialConfigScenario
    //.inject(constantUsersPerSec(Config.users) during (Config.duration seconds))
  .inject(
      incrementUsersPerSec(Config.users)
        .times(Config.times)
        .eachLevelLasting(Config.eachLevelLasting)
        .separatedByRampsLasting(Config.separatedByRampsLasting)
        .startingFrom(Config.startingFrom)
  )


      setUp(getInitialConfigExec)
      .protocols(httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)/*, global.responseTime.max.lt(Config.throughput) */
    )
}