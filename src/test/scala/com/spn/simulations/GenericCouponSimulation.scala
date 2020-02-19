package com.spn.simulations

import com.spn.config.Config
import com.spn.scenarios.PostGenericCouponScenario
import io.gatling.core.Predef.{Simulation, _}

class GenericCouponSimulation extends Simulation {
private val Generic_Coupon = PostGenericCouponScenario.scnGeneric_Coupon
  .inject(rampUsers(Config.rampUp) during(Config.duration) )

  setUp(Generic_Coupon).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0))

}
