package com.spn.scenarios

import com.spn.requests.PostGenericCouponsRequest

import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object PostGenericCouponScenario {
  val datafeeder = csv("data/platform.csv")

  val scnGeneric_Coupon = scenario("POST Generic Coupon")
    .feed(datafeeder)
    .exec(PostGenericCouponsRequest.Generic_Coupons)

}
