package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.BundleIdScenario
import io.gatling.core.Predef.{Simulation, _}

class BundleSimulation extends Simulation
{
private val BundleId = BundleIdScenario.scnbundle
  .inject(rampUsers(Config.rampUp)during(Config.duration)
  )


setUp(BundleId).protocols(Config.httpProtocol)
    .assertions(
        global.failedRequests.count.is(0)
    )
}
