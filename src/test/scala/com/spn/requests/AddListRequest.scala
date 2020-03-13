package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AddListRequest {

  val addList= exec(http("Add list Request")
    .post(Config.app_url + Config.ADD_LIST_URL)
    .headers(Config.sentHeaders)
    .body(StringBody ("""{
    "assets": [
        "${assetID}"
    ]
      }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
