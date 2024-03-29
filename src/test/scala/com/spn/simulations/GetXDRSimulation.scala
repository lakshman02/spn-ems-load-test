package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetXDRScenario
import io.gatling.core.Predef.{Simulation, _}

//simulation
class GetXDRSimulation extends Simulation {
  private val getXDR = GetXDRScenario.scn_Get_XDR
    .inject(
      rampUsers(15) during(30)
    )
  setUp(getXDR).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))
}
