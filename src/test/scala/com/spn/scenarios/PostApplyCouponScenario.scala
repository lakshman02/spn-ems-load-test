package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._
import com.spn.requests.PostApplyCouponRequest

object PostApplyCouponScenario {
  val couponCodeBody = csv("data/couponCode.csv")
  val priceBody = csv("data/price_ApplyCoupon.csv")
  val productBody = csv("data/productId.csv")

  val scnApplyCoupon = scenario("Apply Coupon")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(couponCodeBody)
    .feed(priceBody)
    .feed(productBody)
    .exec(PostApplyCouponRequest.ApplyCoupon)

}
