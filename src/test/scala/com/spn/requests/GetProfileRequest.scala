package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetProfileRequest {

  val getProfile= exec(http("Get Profile Request")
    .get(Config.app_url + Config.GET_PROFILE_URL)
    .headers(Config.sentHeaders)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
