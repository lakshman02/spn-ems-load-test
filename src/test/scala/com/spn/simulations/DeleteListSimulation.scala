package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.DeleteListScenario
import io.gatling.core.Predef.{Simulation, _}

class DeleteListSimulation extends Simulation
{
private val DeleteListExec = DeleteListScenario.deleteListScenario
  .inject(rampUsers(5)during(5)
  )


setUp(DeleteListExec).protocols(Config.httpProtocol)
    .assertions(/*global.responseTime.max.lt(Config.defaultResponseTime),*/
        global.failedRequests.count.is(0)
    )
}
