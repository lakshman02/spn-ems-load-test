package com.spn.requests

import akka.japi.pf.FI.Apply
import io.gatling.http.Predef._
import io.gatling.core.Predef._
import com.spn.config.Config

object PostApplyCouponRequest {

  val ApplyCoupon = http("Apply Coupon")
    .post(Config.app_url + Config.Post_Apply_Coupon_URL)
    .headers(Config.sentHeaders)
    .body(StringBody(
      """ {
                       "couponCode": "${couponCode}",
                        "price": "${price}",
                        "productID": "${productID}",
                        "channelPartnerID": "${channelPartnerID}",
                        "timestamp": "${getDateTime}"
                       }""")).asJson
    //individual csv files are created for body string
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
}
