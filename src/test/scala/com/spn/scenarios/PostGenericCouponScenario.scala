package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.PostGenericCouponsRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object PostGenericCouponScenario {

val langCode = csv("data/languageCode.csv")

  val scnGeneric_Coupon = scenario("Generic Coupon")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(langCode)
    .exec(PostGenericCouponsRequest.Generic_Coupons)

}
