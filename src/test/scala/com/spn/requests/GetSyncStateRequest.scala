package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetSyncStateRequest {
  val getSyncState= exec(http("Get Sync state Request")
    .post(Config.app_url + Config.GetSyncState_URL)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(status is 200)
  )
}
