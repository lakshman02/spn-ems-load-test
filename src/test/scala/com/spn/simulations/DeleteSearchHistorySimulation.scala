package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.DeleteSearchHistoryScenario
import io.gatling.core.Predef.{Simulation, _}

class DeleteSearchHistorySimulation extends Simulation {
private val Apply_Coupon = DeleteSearchHistoryScenario.scnDeleteSearchHistory
  .inject(rampUsers(Config.rampUp) during(Config.duration) )

  setUp(Apply_Coupon).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))

}
