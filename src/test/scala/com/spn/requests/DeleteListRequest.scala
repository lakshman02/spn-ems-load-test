package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object DeleteListRequest {
  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true")

  val deleteList= exec(http("Delete list Request")
    .post(Config.app_url + Config.DELETE_LIST_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
    "assets": [
        "${assetID}"
    ]
}""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
