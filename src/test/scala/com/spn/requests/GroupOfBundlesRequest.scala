package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GroupOfBundlesRequest {
  val groupOfBundles= exec(http("Group Of Bundles Request")
    .get(Config.app_url + Config.GROUP_OF_BUNDLES_URL)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
