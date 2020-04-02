package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object PreviousRequest {
  val Previous = exec(http("Content Previous Request details")

    .get(Config.app_url + Config.PREVIOUS)
    .headers(Config.headerWithoutAuth)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
