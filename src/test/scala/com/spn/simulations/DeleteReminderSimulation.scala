package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.DeleteReminderScenario
import io.gatling.core.Predef.{Simulation, rampUsers, _}

class DeleteReminderSimulation extends Simulation{

  private val DeleteReminderExec = DeleteReminderScenario.deleteReminderScenario
    .inject(
      rampUsers(15) during(15)
    )

  setUp(DeleteReminderExec)
    .protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0) /*, global.responseTime.max.lt(Config.throughput) */
    )

}


