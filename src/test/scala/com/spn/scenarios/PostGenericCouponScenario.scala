package com.spn.scenarios

import com.spn.requests.PostGenericCouponsRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object PostGenericCouponScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederOtpRequirements = csv("data/LoginID.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.circular

  val scnGeneric_Coupon = scenario("POST Generic Coupon")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederOtpRequirements)
    .feed(userCredentials)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .exec(PostGenericCouponsRequest.Generic_Coupons)

}
