package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AddXdrRequest {

  val addXdr= exec(http("Add XDR Request")
    .post(Config.app_url + Config.ADD_XDR_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
    "assetId": "${contentId}",
    "offset": {
      "assetDuration": ${assetDuration},
      "position": ${position}
       },
    "updatedTime": ${updatedTime},
    "isOnAir": ${isOnAir}
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
