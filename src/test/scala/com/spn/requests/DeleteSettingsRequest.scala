package com.spn.requests

import io.gatling.core.Predef._
import io.gatling.http.Predef._

import com.spn.config.Config

object DeleteSettingsRequest {
  val Delete_Settings = exec(http("Delete Settings")
    .post(Config.app_url + Config.DELETE_SETTINGS_URL)
    .headers(Config.sentHeadersNew)
    .check(status.is(200))
    .check(jsonPath("$.resultCode") is ("OK"))
  )
}
