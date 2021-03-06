package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.ProductsByCouponRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object ProductsByCouponScenario{

  val productsByCouponScenario =scenario("Products By Coupon Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .exec(ApiSecurity.getToken)
    .exec(ProductsByCouponRequest.productsByCoupon)
}
