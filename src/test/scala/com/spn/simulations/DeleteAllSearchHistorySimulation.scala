package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.DeleteAllSearchHistoryScenario
import io.gatling.core.Predef.{Simulation, _}

class DeleteAllSearchHistorySimulation extends Simulation {
private val Apply_Coupon = DeleteAllSearchHistoryScenario.scnDeleteAllSearchHistory
  .inject(rampUsers(1) during(1) )

  setUp(Apply_Coupon).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))

}
