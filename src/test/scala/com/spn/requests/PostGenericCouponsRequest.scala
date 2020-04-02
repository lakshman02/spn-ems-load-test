package com.spn.requests

import io.gatling.core.Predef._
import com.spn.config.Config
import io.gatling.http.Predef._
import com.spn.common.Constants


object PostGenericCouponsRequest {

  val Generic_Coupons = exec(http("Generic Coupon")
    .post(Config.app_url + Config.Post_Generic_Coupon)
    .headers(Config.sentHeaders)
    .body(StringBody(
      """
        {
          "channelPartnerID": "${channelPartnerID}",
          "languageCode": "${languageCode}",
          "timestamp": "${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$.resultCode").saveAs(Constants.RESP_GENERIC_COUPON))
  )

}
