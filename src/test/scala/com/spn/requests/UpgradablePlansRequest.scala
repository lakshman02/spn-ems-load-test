package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object UpgradablePlansRequest {
  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true")

  val upgradablePlans= exec(http("Upgradable Plans Request")
    .post(Config.app_url + Config.UPGRADABLE_PLANS_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
    "skuORQuickCode": "${skuORQuickCode}",
    "channelPartnerID": "${channelPartnerID}",
    "timestamp": "${getDateTime}"
        }""")).asJson
    .check(jsonPath("$.resultCode").is("OK"))
    .check(status is 200)
  )
}
