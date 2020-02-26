package com.spn.simulations

import com.spn.config.Config
import io.gatling.core.Predef._
import com.spn.scenarios.ProcessRazorPayOrderScenario
import io.gatling.core.Predef.{Simulation, _}

class ProcessRazorPayOrderSimulation extends Simulation{
  private val ProcessRazorPayOrderExec = ProcessRazorPayOrderScenario.processRazorPayOrderScenario
    .inject(rampUsers(Config.rampUp) during(Config.duration) )

  setUp(ProcessRazorPayOrderExec).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))
}


