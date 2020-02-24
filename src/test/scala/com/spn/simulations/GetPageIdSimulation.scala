package com.spn.simulations

import io.gatling.core.Predef.Simulation
import com.spn.config.Config
import com.spn.scenarios.GetPageIdScenario
import io.gatling.core.Predef._

class GetPageIdSimulation extends Simulation
{
private val getPageId = GetPageIdScenario.PageId
  .inject(rampUsers(5)during(Config.duration)
  )


setUp(getPageId).protocols(Config.httpProtocol)
    .assertions(
        global.failedRequests.count.is(0)
    )
}
