package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetSyncStateRequest {
  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true")
  val getSyncState= exec(http("Get Sync state Request")
    .get(Config.app_url + Config.GetSyncState_URL)
    .headers(sentHeaders)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
