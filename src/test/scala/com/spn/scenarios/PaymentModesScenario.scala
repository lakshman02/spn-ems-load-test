package com.spn.scenarios

import io.gatling.core.Predef._
import com.spn.requests.PaymentModesRequest
object PaymentModesScenario {
val bodydatafeeder = csv("data/LoginID.csv")
  val datafeeder = csv("data/platform.csv")
  val scnPaymentMode = scenario("Post Payment Mode")
    .feed(CreateOTPScenario.dateTimeFeeder)
    .feed(bodydatafeeder)
    .feed(datafeeder)
    .exec(PaymentModesRequest.Payment_mode)
}
