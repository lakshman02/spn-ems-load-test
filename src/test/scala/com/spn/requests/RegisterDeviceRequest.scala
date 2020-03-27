package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.spn.common.Constants

object RegisterDeviceRequest {
  val registerDevice= exec(http("Register Device Request")
    .post(Config.app_url + Config.REGISTER_DEVICE_URL)
    .formParam("activationCode","${activationCode}")
    .formParam("channelPartnerID","${channelPartnerID}")
    .headers(Config.sentHeadersNew)
//    .body(StringBody ("""{
//             "channelPartnerID": "${channelPartnerID}"
//        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
