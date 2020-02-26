package com.spn.requests

import akka.actor.FSM.->
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GenerateDeviceActivationCodeRequest {

  val generateDeviceActivationCode= exec(http("Generate Device Activation Code Request")
    .post(Config.app_url + Config.GENERATE_DEVICE_ACTIVATION_CODE_URL)
    .header("x-via-device", "true")
    .body(StringBody ("""{
             "channelPartnerID": "${channelPartnerID}",
             "deviceName": "${deviceName}",
             "deviceModelNumber": "${deviceModelNumber}",
             "serialNo": "d6acc46e-5a09-d432-1afb-d8c4eb6dd811"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
