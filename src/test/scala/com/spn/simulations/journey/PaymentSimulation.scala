package com.spn.simulations.journey

import com.spn.config.Config
import com.spn.scenarios.journey.PaymentScenario
import io.gatling.core.Predef.{Simulation, _}

class PaymentSimulation extends Simulation {
  private val paymentExec = PaymentScenario.doPaymentOperationsScenario
    .inject(
      rampUsers(50)during(50)
    )

  setUp(paymentExec).protocols(Config.httpProtocol)
    .assertions(
      global.failedRequests.count.is(0)
    )
}