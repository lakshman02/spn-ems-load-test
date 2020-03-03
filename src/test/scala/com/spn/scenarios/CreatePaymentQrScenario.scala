package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.common.CommonFeedFiles
import com.spn.requests.CreatePaymentQrRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object CreatePaymentQrScenario {


  val dataFeederOtpRequirements = csv("data/payment_details.csv").circular

  val createPaymentQrScenario =scenario("Create Payment QR Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
.feed(dataFeederOtpRequirements)
    .feed(CommonFeedFiles.userAuth1KUsers)
.feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .exec(CreatePaymentQrRequest.createPaymentQrRequest)
}
