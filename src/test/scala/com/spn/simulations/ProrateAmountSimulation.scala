package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.ProrateAmountScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

class ProrateAmountSimulation extends Simulation {

  private val ProrateAmountSimulation = ProrateAmountScenario.prorateAmountScenario
    //.inject(constantUsersPerSec(Config.users) during (Config.duration seconds))
    .inject(
      rampUsers(15) during(30)
    )

  setUp(ProrateAmountSimulation)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )
}


