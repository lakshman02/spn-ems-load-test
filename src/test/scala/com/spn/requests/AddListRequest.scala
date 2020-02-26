package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AddListRequest {

  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true")

  val addList= exec(http("Add list Request")
    .post(Config.app_url + Config.ADD_LIST_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
    "assets": [
        "1000000189",
        "1000000190",
        "1000000191",
        "1000000192"
    ]
      }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
