package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.PreviewAddScenario
import io.gatling.core.Predef.{Simulation, _}

class PreviewaddSimulation extends Simulation {
  private val previewAddExec = PreviewAddScenario.previewAddScenario
    .inject(rampUsers(15) during (30))


  setUp(previewAddExec).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0)
    )
}