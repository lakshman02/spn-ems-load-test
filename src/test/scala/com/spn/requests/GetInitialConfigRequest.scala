package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetInitialConfigRequest {

  val getInitialConfig = exec(http("Get Initial Config Request")
    .get(Config.app_url + Config.URL_INITIAL_CONFIG)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))

  )
}