package com.spn.scenarios

import com.spn.requests.PostGenericCouponsRequest

import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object PostGenericCouponScenario {
  val datafeeder = csv("data/platform.csv")
  val bodydatafeeder = csv("data/LoginID.csv")
  val userAuthFeeder = csv ("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard

  val scnGeneric_Coupon = scenario("POST Generic Coupon")
    .feed(datafeeder)
    .feed(bodydatafeeder)
    .feed(userAuthFeeder)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .exec(PostGenericCouponsRequest.Generic_Coupons)

}
