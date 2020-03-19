package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object RemoveDevicesRequest {

  val removeDevicesRequest = exec(http("Remove Devices Request")
    .get(Config.app_url + Config.REMOVE_DEVICES_URL)
    .headers(Config.sentHeadersNew)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )

}


