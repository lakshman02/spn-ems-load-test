package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.PaymentModesRequest
import io.gatling.core.Predef._
object PaymentModesScenario {

 val scnPaymentMode = scenario("Payment Modes")
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .exec(PaymentModesRequest.Payment_mode)
}
