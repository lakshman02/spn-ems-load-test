package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AllSubscriptionsRequest {

  val getAllSubscriptions= exec(http("Get all subscription of the user")
    .post(Config.app_url + Config.GET_ALL_SUBSCRIPTIONS_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
              "channelPartnerID": "${channelPartnerID}",
              "timestamp":"${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
  //  .pause(1)
}
