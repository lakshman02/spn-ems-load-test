package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.AddSearchedItemScenario
import io.gatling.core.Predef.{Simulation, _}

class AddSearchedItemSimulation extends Simulation
{
private val addSearchedItem = AddSearchedItemScenario.scnAddSearchedItem
  .inject(rampUsers(Config.rampUp)during(Config.duration)
  )


setUp(addSearchedItem).protocols(Config.httpProtocol)
    .assertions(
        global.failedRequests.count.is(0)
    )
}
