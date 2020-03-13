package com.spn.requests

import io.gatling.core.Predef._
import com.spn.config.Config
import io.gatling.http.Predef._

object GetDevicesRequest {

  val getDevicesRequest = exec(http("Get Devices Request")
    .get(Config.app_url + Config.GET_DEVICES_URL)
    .headers(Config.sentHeadersNew)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )

}


