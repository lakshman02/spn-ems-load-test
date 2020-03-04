package com.spn.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import com.spn.config.Config

object PostSubscriptionHistoryRequest {

  val subscriptionHistory = exec(http("Subscription History")
    .post(Config.app_url + Config.Post_Subscription_History)
      .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}",
        "x-via-device" -> "true" ))
  .body(StringBody("""{
                       "channelPartnerID": "${channelPartnerID}",
                       "timestamp": "${getDateTime}"
                     } """)).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
