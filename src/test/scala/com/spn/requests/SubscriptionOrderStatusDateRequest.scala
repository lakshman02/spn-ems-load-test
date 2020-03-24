package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SubscriptionOrderStatusDateRequest {

  val subscriptionOrderStatusDateRequest= exec(http("SubscriptionOrderStatusDate Request")
    .post(Config.app_url + Config.SUBSCRIPTION_ORDER_STATUS_DATE_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
    "start_date": ${start_date},
    "end_date": ${end_date},
        "page_limit": "${page_limit}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
