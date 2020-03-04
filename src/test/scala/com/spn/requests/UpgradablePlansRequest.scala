package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object UpgradablePlansRequest {

  val upgradablePlans= exec(http("Upgradable Plans Request")
    .post(Config.app_url + Config.UPGRADABLE_PLANS_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
    "skuORQuickCode": "${skuORQuickCode}",
    "channelPartnerID": "${channelPartnerID}",
    "timestamp": "${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
