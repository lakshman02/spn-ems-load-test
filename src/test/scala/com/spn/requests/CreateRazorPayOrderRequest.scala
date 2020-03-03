package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CreateRazorPayOrderRequest {
  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true")

  val createRazorPayOrder= exec(http("Create Razor Pay Order Request")
    .post(Config.app_url + Config.CREATE_RAZOR_PAY_ORDER_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
    "serviceID": "${serviceID}",
    "serviceType": "${serviceTypeTVOD}",
    "timestamp": "${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
