package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object StoreDropOffReasonRequest {

  val storeDropOffReason= exec(http("Store Drop Off Reason Request")
    .post(Config.app_url + Config.STORE_DROP_OFF_REASON_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
    "reasonCode": "${reasonCode}",
    "reasonDescription": "${reasonDescription}",
    "serviceID": "${serviceIDNew}",
    "serviceType": "${serviceType}",
    "channelPartnerID": "${channelPartnerID}",
    "timestamp": "${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
