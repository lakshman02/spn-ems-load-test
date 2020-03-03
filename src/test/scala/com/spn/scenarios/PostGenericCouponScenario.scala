package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.PostGenericCouponsRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object PostGenericCouponScenario {

  val dataFeederOtpRequirements = csv("data/LoginID.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val scnGeneric_Coupon = scenario("POST Generic Coupon")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(dataFeederOtpRequirements)
    .feed(userCredentials)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .exec(PostGenericCouponsRequest.Generic_Coupons)

}
