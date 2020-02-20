package com.spn.requests

import io.gatling.core.Predef._
import com.spn.config.Config
import io.gatling.http.Predef._


object PostGenericCouponsRequest {

  val Generic_Coupons = exec(http("POST Generic Coupon")
    .post(Config.app_url + Config.Post_Generic_Coupon)
    .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}",
      "x-via-device" -> "true", "Content-Type" -> "application/json"))
    .body(StringBody(
      """
        {
          "channelPartnerID": "${channelPartnerID}",
          "languageCode": "en_IN",
          "timestamp": "${getDateTime}"
        }""")).asJson
      .check(status.is(200))
      .check(jsonPath("$.resultCode").is("OK"))
  )

}
