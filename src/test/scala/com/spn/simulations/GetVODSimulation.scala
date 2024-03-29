package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.{GetPageIdScenario, VODDetailsScenario}
import io.gatling.core.Predef.{Simulation, _}

class GetVODSimulation extends Simulation
{
private val getPageId = VODDetailsScenario.vodDetailsScenario
    .inject(atOnceUsers(3))

  setUp(getPageId).protocols(Config.httpProtocol)
    .assertions(/*global.responseTime.max.lt(Config.defaultResponseTime),*/
        global.failedRequests.count.is(0)
    )
}
