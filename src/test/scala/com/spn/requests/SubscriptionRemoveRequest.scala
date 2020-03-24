package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SubscriptionRemoveRequest {

  val subscriptionRemove= exec(http("Remove subscription request")
    .post(Config.app_url + Config.SUBSCRIPTION_REMOVE_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
            "serviceID": "${service_id_new}",
             "reason": "sb load testing",
             "serviceType": "${service_type}",
             "channelPartnerID": "${single_channel_partner_id}",
             "timestamp": "${getDateTime}"
                 }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
