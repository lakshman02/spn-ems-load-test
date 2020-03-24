package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LogoutRequest {

  val logoutRequest = exec(http("Logout Request")
    .get(Config.app_url + Config.LOGOUT_URL)
    .headers(Config.sentHeaders)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )

}

