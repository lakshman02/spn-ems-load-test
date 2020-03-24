package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.PaymentModesRequest
import io.gatling.core.Predef._

object PaymentModesScenario {
  val langBody = csv("data/languageCode.csv").circular
  val serviceBody = csv("data/single_serviceId.csv").circular
  val platformBody = csv("data/platform_PaymentMode.csv").circular
  val appTypeBody = csv("data/appType.csv").circular
  val channelPartneridBody = csv("data/single_channel_partner_id.csv").circular

  val scnPaymentMode = scenario("Payment Modes")
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(langBody)
    .feed(channelPartneridBody)
    .feed(serviceBody)
    .feed(platformBody)
    .feed(appTypeBody)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .exec(ApiSecurity.getToken)
    .exec(PaymentModesRequest.Payment_mode)
}
