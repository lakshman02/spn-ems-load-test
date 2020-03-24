package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object ShowDetailRequest {
  val showDetailRequest  = exec(http("Show Detail Request")
    .get(Config.app_url + Config.SHOW_DETAIL_URL )
    .headers(Config.secHeader)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
