package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object GetXDRRequest {
  val getXDR = exec(http("Get XDR Request")

    .get(Config.app_url + Config.GET_XDR)
      .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}",
        "x-via-device" -> "true"))
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
