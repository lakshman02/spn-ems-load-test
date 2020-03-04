package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ProductsByCouponRequest {

  val productsByCoupon= exec(http("Products By Coupon Request")
    .post(Config.app_url + Config.PRODUCTS_BY_COUPON_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
             "voucherCode": "${couponCode}",
    "channelPartnerID": "${channelPartnerID}",
    "timestamp": "${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))

  )
}
