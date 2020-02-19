package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.PaymentModesScenario
import io.gatling.core.Predef.{Simulation, _}

class PaymentModeSimulation extends Simulation {
private val Payment_Mode = PaymentModesScenario.scnPaymentMode
  .inject(rampUsers(Config.rampUp) during(Config.duration) )

  setUp(Payment_Mode).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))

}
