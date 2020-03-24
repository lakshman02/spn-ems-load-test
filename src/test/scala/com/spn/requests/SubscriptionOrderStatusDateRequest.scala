package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SubscriptionOrderStatusDateRequest {

  val subscriptionOrderStatusDateRequest= exec(http("SubscriptionOrderStatusDate Request")
    .post(Config.app_url + Config.SUBSCRIPTION_ORDER_STATUS_DATE_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
    "startDateTime": ${startDateTime},
    "endDateTime": ${endDateTime},
        "page_limit": "${page_limit}",
    "last_evaluated_key": {
      "PK":"${PK}",
    "SK":"${SK}"
    }
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
