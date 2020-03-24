package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.spn.common.Constants

object GetTokenRequest {

  val getToken= exec(http("Get Token Request")
    .get(Config.app_url + Config.GET_TOKEN_URL)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$.resultObj").saveAs(Constants.RESP_SECURITY_TOKEN))
  )
}
