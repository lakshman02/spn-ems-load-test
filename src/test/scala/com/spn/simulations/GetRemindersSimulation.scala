package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetRemindersScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

class GetRemindersSimulation extends Simulation{

  private val getRemindersExec = GetRemindersScenario.getRemindersScenario
    .inject(
      rampUsers(15) during(30)
    )

  setUp(getRemindersExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


