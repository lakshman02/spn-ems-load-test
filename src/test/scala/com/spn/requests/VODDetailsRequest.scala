package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object VODDetailsRequest {

  val vodDetails= exec(http("VOD Details")
    .get(Config.app_url + Config.VOD_DETAILS)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
