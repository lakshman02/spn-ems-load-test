package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object ActiveSubscription  {

  val ActiveSubscription   = exec(http("Active Subscription Request")

    .post(Config.app_url + Config.ACTIVE_SUBSCRIPTIONS_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
  "channelPartnerID": "${channelPartnerID}",
  "timestamp": "${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}