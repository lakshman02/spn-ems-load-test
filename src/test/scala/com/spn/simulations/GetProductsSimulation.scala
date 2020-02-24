package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.GetProductScenario
import io.gatling.core.Predef.{Simulation, _}

class GetProductsSimulation extends Simulation {

  private val productsScenarioExec = GetProductScenario.getProductScenario
    .inject(rampUsers(Config.rampUp) during(Config.duration) )

  setUp(productsScenarioExec).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))

}


