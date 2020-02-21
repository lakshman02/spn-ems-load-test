package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ProductsByCouponRequest {
  val sentHeaders = Map(
    "Authorization" -> "",
    "x-via-device" -> "true")

  val productsByCoupon= exec(http("Products By Coupon Request")
    .post(Config.app_url + Config.PRODUCTS_BY_COUPON_URL)
      .headers(sentHeaders)
    .body(StringBody ("""{
             "voucherCode": "${gen_CouponCode}",
    "channelPartnerID": "${channelPartnerID}",
    "timestamp": "${getDateTime}"
        }""")).asJson
    .check(jsonPath("$.resultCode").is("OK"))
    .check(status is 200)
  )
}
