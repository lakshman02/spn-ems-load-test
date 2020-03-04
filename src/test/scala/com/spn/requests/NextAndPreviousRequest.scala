package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object NextAndPreviousRequest {

  val nextAndPreviousRequest = exec(http("Next & Previous Request")
    .get(Config.app_url + Config.NEXT_AND_PREVIOUS_URL)
    .header("x-via-device","true")
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}


