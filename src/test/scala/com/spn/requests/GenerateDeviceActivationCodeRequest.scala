package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.spn.common.Constants

object GenerateDeviceActivationCodeRequest {

  val generateDeviceActivationCode= exec(http("Generate Device Activation Code Request")
    .post(Config.app_url + Config.GENERATE_DEVICE_ACTIVATION_CODE_URL)
    .headers(Config.devSecHeader)
    .body(StringBody ("""{
             "channelPartnerID": "${channelPartnerID}",
             "deviceName": "${deviceName}",
             "deviceModelNumber": "${deviceModelNumber}",
             "serialNo": "${serialNo}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$.resultObj").saveAs(Constants.RESP_ACTIVATION_CODE_COMPLETE_RESPONSE))
      .check(checkIf(session => session(Constants.RESP_ACTIVATION_CODE_COMPLETE_RESPONSE).as[String].contains("activationCode")){
        jsonPath("$.resultObj.activationCode").saveAs(Constants.RESP_ACTIVATION_CODE)
      })
  )
}

