package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object IsSubscribedRequest {
  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true")

  val isSubscribed= exec(http("Is Subscribed Request")
    .post(Config.app_url + Config.IS_SUBSCRIBED_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
         "isContent": "true",
          "id": "${TVODID}",
          "type": "${ShowType}",
          "showName": "${ShowName}",
           "channelPartnerID": "${channelPartnerID}",
           "timestamp": "${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
