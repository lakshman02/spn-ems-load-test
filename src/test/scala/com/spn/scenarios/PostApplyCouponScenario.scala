package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._
import com.spn.requests.PostApplyCouponRequest

object PostApplyCouponScenario {

 val dataFeederOtpRequirements = csv("data/LoginID.csv").circular

val scnApplyCoupon = scenario ("Apply Coupon")
  .feed(CommonFeedFiles.dataFeederChannel)
  .feed(CommonFeedFiles.dataFeederLocale)
  .feed(CommonFeedFiles.dataFeederCluster)
  .feed(CommonFeedFiles.dataFeederTenant)
  .feed(CommonFeedFiles.dataFeederProperty)
  .feed(dataFeederOtpRequirements)
  .feed(CommonFeedFiles.userAuth1KUsers)
  .feed(CommonFeedFiles.dateTimeFeeder)
  .exec(PostApplyCouponRequest.ApplyCoupon)

}
