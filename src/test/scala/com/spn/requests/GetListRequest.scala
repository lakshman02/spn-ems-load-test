package com.spn.requests

import io.gatling.core.Predef._
import com.spn.config.Config
import io.gatling.http.Predef._

object GetListRequest {

  val getUserListRequest = exec(http("Get My List Request")
    .get(Config.app_url + Config.GET_LIST)
    .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}",
      "x-via-device" -> "true"))
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )

}
