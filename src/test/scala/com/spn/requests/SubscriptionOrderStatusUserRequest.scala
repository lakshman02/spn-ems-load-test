package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SubscriptionOrderStatusUserRequest {

  val subscriptionOrderStatusUserRequest= exec(http("SubscriptionOrderStatusUser Request")
    .post(Config.app_url + Config.SUBSCRIPTION_ORDER_STATUS_USER_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
        "page_limit": "${page_limit}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
