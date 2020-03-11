package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object AddSettingsRequest  {

  val addSettings = exec(http("Add Settings Request")

    .post(Config.app_url + Config.ADD_SETTINGS_URL)
    .headers(Config.sentHeadersNew)
    .body(StringBody ("""{
    "videoStreamingQuality": "2"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}