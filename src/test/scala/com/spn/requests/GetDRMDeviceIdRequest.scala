package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetDRMDeviceIdRequest {

  val getDRMDeviceIdRequest = exec(http("Get DRM Device Id Request")
    .post(Config.app_url + Config.GET_DRM_DEVICEID_URL)
    .headers(Config.sentHeaders)
    .body(StringBody("""{
                       "platform": "${platform_name}",
                       "deviceId": "${deviceId}",
                       "actionType": "${actionType}",
                       "browser": "${browser}",
                       "os": "${os}"
                       }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))

}

