package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object NextRequest {

  val nextRequest = exec(http("Next Request")
    .get(Config.app_url + Config.NEXT_URL)
    .header("x-via-device","true")
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )

}
