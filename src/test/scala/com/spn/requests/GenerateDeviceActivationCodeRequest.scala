package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.spn.common.Constants

object GenerateDeviceActivationCodeRequest {

  val generateDeviceActivationCode= exec(http("Generate Device Activation Code Request")
    .post(Config.app_url + Config.GENERATE_DEVICE_ACTIVATION_CODE_URL)
    .header("x-via-device", "true")
    .body(StringBody ("""{
             "channelPartnerID": "${channelPartnerID}",
             "deviceName": "${deviceName}",
             "deviceModelNumber": "${deviceModelNumber}",
             "serialNo": "${serialNo}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$.resultObj.activationCode").findRandom.saveAs(Constants.RESP_ACTIVATION_CODE)
  ))
    .exec(session => {
      val printActivationCode = session(Constants.RESP_ACTIVATION_CODE).asOption[String]
      println("======================================================")
      println("activationCode: =====>> " + printActivationCode.getOrElse("COULD NOT FIND activationCode"))
      println("======================================================")
      session})
}
