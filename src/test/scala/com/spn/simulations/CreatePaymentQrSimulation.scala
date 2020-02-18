package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.CreatePaymentQrScenario
import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

class CreatePaymentQrSimulation extends Simulation
{
private val createPaymentQr = CreatePaymentQrScenario.createPaymentQrScenario
  .inject(incrementUsersPerSec(Config.users)
    .times(Config.times)
    .eachLevelLasting(Config.eachLevelLasting)
    .separatedByRampsLasting(Config.separatedByRampsLasting)
    .startingFrom(Config.startingFrom)
  )


setUp(createPaymentQr).protocols(Config.httpProtocol)
    .assertions(/*global.responseTime.max.lt(Config.defaultResponseTime),*/
        global.failedRequests.count.is(0)
    )
}
