package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
object ActiveSubscription  {

  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true",
    "Content-Type" -> "application/json"
  )
  val ActiveSubscription   = exec(http("Active Subscription Request")
    .post(Config.app_url + Config.ACTIVE_SUBSCRIPTIONS_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
  "channelPartnerID": "MSMIND",
  "timestamp": "${getDateTime}"
        }""")).asJson
    .check(jsonPath("$.resultCode").is("OK")))
}