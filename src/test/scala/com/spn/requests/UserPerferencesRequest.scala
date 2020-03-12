package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object UserPerferencesRequest {

  val userPerferencesAPI= exec(http("User Preferences Request")
    .get(Config.app_url + Config.USER_PERFERENCES_URL)
    .headers(Config.sentHeaders)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
