package com.spn.requests

import io.gatling.http.Predef._
import io.gatling.core.Predef._

import com.spn.config.Config
object PostApplyCouponRequest {

  val ApplyCoupon = http("Post Apply Coupon")
    .post (Config.app_url + Config.Post_Apply_Coupon_URL)
    .headers(Map ("Authorization" -> "${RESP_AUTH_TOKEN}",
      "x-via-device" -> "true"))

    .body(StringBody(""" {
                       "couponCode": "SONYTEST",
                        "price": 1.99,
                        "productID": "daily_india",
                        "channelPartnerID": "${channelPartnerID}",
                        "timestamp": "${getDateTime}"
                       }""")).asJson
    //Hardcoded body string
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
}
