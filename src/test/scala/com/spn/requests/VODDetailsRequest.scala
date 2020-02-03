package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object VODDetailsRequest {

  val vodDetails= exec(http("VOD Details")
    .get(Config.app_url + Config.VOD_DETAILS)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(status is 200)
  ).pause(1)
}
