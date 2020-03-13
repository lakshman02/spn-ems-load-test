package com.spn.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import com.spn.config.Config

object PaymentModesRequest {
  val Payment_mode = exec(http("Payment Modes")
    .post(Config.app_url + Config.PAYMENT_MODES_URL)
    .headers(Config.sentHeaders)
      .body(StringBody(""" {
                           "serviceID": "${single_service_id}",
                           "serviceType": "${single_service_type}",
                           "channelPartnerID": "${single_channel_partner_id}",
                           "platform": "${platform}",
                           "appType": "${appType}",
                           "deviceType": "${deviceType}",
                           "languageCode": "${languageCode}",
                           "timestamp": "${getDateTime}"
                         }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
