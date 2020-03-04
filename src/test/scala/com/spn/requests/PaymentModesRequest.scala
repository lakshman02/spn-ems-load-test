package com.spn.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import com.spn.config.Config

object PaymentModesRequest {
  val Payment_mode = exec(http("Payment Modes")
    .post(Config.app_url + Config.PAYMENT_MODES_URL)
  .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true"))
      .body(StringBody(""" {
                           "serviceID": "1mn_99_india",
                           "serviceType": "SVOD",
                           "channelPartnerID": "${channelPartnerID}",
                           "platform": "Desktop",
                           "appType": "Web",
                           "deviceType": "${deviceType}",
                           "languageCode": "en-IN",
                           "timestamp": "${getDateTime}"
                         }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
