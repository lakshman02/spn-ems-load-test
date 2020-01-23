package com.spn.simulations

import io.gatling.core.Predef.Simulation
import com.spn.config.Config
import com.spn.scenarios.GetPageIdScenario
import io.gatling.core.Predef._

class GetPageId extends Simulation
{
private val getPageId = GetPageIdScenario.PageID
  .inject(constantConcurrentUsers(Config.users)during(Config.duration))
setUp(getPageId).protocols(Config.httpProtocol)
    .assertions(global.responseTime.max.lt(Config.defaultResponseTime),global.failedRequests.count.is(0)
    )
}
