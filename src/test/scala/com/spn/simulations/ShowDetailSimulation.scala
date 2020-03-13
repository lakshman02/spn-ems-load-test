package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.ShowDetailScenario
import io.gatling.core.Predef.{Simulation, _}

class ShowDetailSimulation extends Simulation
{
private val showDetail = ShowDetailScenario.showDetailScenario
  .inject(
    rampUsers(15) during(30)
  )



setUp(showDetail).protocols(Config.httpProtocol)
    .assertions(/*global.responseTime.max.lt(Config.defaultResponseTime),*/
        global.failedRequests.count.is(0)
    )
}
