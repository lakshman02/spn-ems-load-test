package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetLAUrlRequest {

  val getLaUrl = exec(http("Get LA URL Request")
    .post(Config.app_url + Config.LA_URL)
    .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}"))
    .body(StringBody("""{
                       "platform": "${platform_name}",
                       "deviceId": "${deviceId}",
                       "actionType": "${actionType}",
                       "browser": "${browser}",
                       "os": "${os}"
                       }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))

}


