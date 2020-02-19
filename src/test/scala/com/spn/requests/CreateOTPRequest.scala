package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CreateOTPRequest {

  val otpHeader = Map("x-via-device" -> "true")

  val createOTPRequest = exec(http("Create OTP Request")
   .post(Config.app_url + Config.CREATE_OTP_URL)
   .headers(otpHeader)
    .body(StringBody("""{
        |"channelPartnerID": "${channelPartnerID}",
        |"mobileNumber": "${evg_phone_number}",
        |"country": "${country}",
        |"timestamp": "${getDateTime}"
        |}""".stripMargin)).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))

}
