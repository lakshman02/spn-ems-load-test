package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CreatePaymentQrRequest {

  val createPaymentQrRequest = exec(http("Create paymentQrRequest")
    .post(Config.app_url + Config.CREATE_PAYMENT_QR )
    .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}",
      "x-via-device" -> "true"))
    .body(StringBody("""{
                       "channelPartnerID": "${channelPartnerID}",
                       "serviceID": "${serviceID}",
                       "serviceType": "${serviceType}",
                       "paymentChannel":"${paymentChannel}"
                       "timestamp": "${getDateTime}",
                       }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
