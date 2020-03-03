package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetDRMDeviceIdRequest {

  val getDRMDeviceIdRequest = exec(http("Get DRM Device Id Request")
    .post(Config.app_url + Config.GET_DRM_DEVICEID_URL)
    .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}"))
    .body(StringBody("""{
                       "platform": "${channel}",
                       "deviceId": "5264B354636343D-7C89-4FDB-43564rA5FB-D74475343690F28C0775555354675675464559",
                       "actionType": "${actionType}",
                       "browser": "${browser}",
                       "os": "${os}"
                       }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))

}

