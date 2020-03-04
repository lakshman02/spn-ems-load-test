package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object ProcessRazorPayOrderRequest {

  val processRazorPayOrderRequest = exec(http("ProcessRazorPayOrderRequest")
    .post(Config.app_url + Config.PROCESSRAZORPAYORDER_URL )
    .headers(Config.sentHeaders)
    .body(StringBody("""{
                       "paymentID": "${paymentID}",
                       "timestamp": "${getDateTime}",
                       }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
