package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

//TODO observation: duplicate of ContentDetailBundle
object BundleIdRequest {
  val BundleId = exec(http("Get Bundle Id Details")
    .get(Config.app_url + Config.BUNDLE)
    .headers(Config.secHeader)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
