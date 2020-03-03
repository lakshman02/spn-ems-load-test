package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object PreviousRequest {
  val Previous = exec(http("Content Previous Request details")

    .get(Config.app_url + Config.PREVIOUS)
      .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}",
        "x-via-device" -> "true"))
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
