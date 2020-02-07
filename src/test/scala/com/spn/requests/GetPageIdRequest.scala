package com.spn.requests

import io.gatling.core.Predef._
import com.spn.config.Config
import io.gatling.http.Predef._


object GetPageIdRequest {
  val PageId = exec(http("Get Page Id")
    .get(Config.app_url + Config.GET_PageID)
//    .check(status.not("400"),status.not("404"),status.not("500"),status.not("503"))
    .check(jsonPath("$.resultCode").is("OK")))
}
