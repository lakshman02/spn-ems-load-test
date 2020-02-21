package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object StoreDropOffReasonRequest {
  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true")

  val storeDropOffReason= exec(http("Store Drop Off Reason Request")
    .post(Config.app_url + Config.STORE_DROP_OFF_REASON_URL)
      .headers(sentHeaders)
    .body(StringBody ("""{
    "reasonCode": "1001",
    "reasonDescription": "Content not good",
    "serviceID": "1mn_99_india",
    "serviceType": "${serviceType}",
    "channelPartnerID": "${channelPartnerID}",
    "timestamp": "${getDateTime}"
        }""")).asJson
    .check(jsonPath("$.resultCode").is("OK"))
    .check(status is 200)
  )
}
