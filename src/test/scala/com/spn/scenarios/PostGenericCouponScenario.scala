package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.PostGenericCouponsRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object PostGenericCouponScenario {

val langCode = csv("data/languageCode.csv").circular

  val scnGeneric_Coupon = scenario("Generic Coupon")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(langCode)
    .exec(ApiSecurity.getToken)
    .exec(PostGenericCouponsRequest.Generic_Coupons)

}
