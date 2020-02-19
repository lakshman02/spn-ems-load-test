package com.spn.simulations

import io.gatling.core.Predef.Simulation
import io.gatling.core.Predef._

import com.spn.scenarios.PostApplyCouponScenario
import com.spn.config.Config

class PostApplyCouponSimulation extends Simulation {
private val Apply_Coupon = PostApplyCouponScenario.scnApplyCoupon
  .inject(rampUsers(Config.rampUp) during(Config.duration) )

  setUp(Apply_Coupon).protocols(Config.httpProtocol)
    .assertions(global.failedRequests.count.is(0),global.responseTime.max.between(100,300))

}
