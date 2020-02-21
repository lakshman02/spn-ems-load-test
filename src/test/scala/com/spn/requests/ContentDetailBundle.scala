package com.spn.requests

import io.gatling.core.Predef._
import com.spn.config.Config
import io.gatling.http.Predef._


object ContentDetailBundle {
  val ContentDetailBundle  = exec(http("Content Detail Bundle")
    .get(Config.app_url + Config.CONTENT_DETAIL_BUNDLE_URL )
      .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
