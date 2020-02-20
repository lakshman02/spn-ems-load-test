package com.spn.scenarios

import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

import com.spn.requests.PostApplyCouponRequest

object PostApplyCouponScenario {
 val  data_feeder = csv("data/LoginID.csv").queue
  val dataApplyCoupon = csv("data/platform.csv").queue
 val userAuthFeeder = csv ("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard

val scnApplyCoupon = scenario ("Post Apply Coupon")
  .feed(data_feeder)
  .feed(CreateOTPScenario.dateTimeFeeder)
  .feed(userAuthFeeder)
  .feed(dataApplyCoupon)
  .exec(PostApplyCouponRequest.ApplyCoupon)

}
