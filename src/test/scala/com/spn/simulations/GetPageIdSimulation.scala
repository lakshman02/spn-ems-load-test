package com.spn.simulations

import io.gatling.core.Predef.Simulation
import com.spn.config.Config
import com.spn.scenarios.GetPageIdScenario
import io.gatling.core.Predef._

class GetPageIdSimulation extends Simulation
{
private val getPageId = GetPageIdScenario.PageId
  .inject(rampUsers(30)during(30)
  )


setUp(getPageId).protocols(Config.httpProtocol)
    .assertions(
        global.failedRequests.count.is(0)
    )
}
