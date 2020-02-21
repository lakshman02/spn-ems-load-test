package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SubscriptionRemoveRequest {
  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true")

  val subscriptionRemove= exec(http("SubscribedRemove Request")
    .post(Config.app_url + Config.SUBSCRIPTION_REMOVE_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
  "serviceID": "${skuORQuickCode}",
  "reason": "testing",
  "serviceType": "${serviceType}",
  "channelPartnerID": "${channelPartnerID}",
  "timestamp": "${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
