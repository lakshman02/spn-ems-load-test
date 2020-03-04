package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object CreateOTPRequest {

  val createOTPRequest = exec(http("Create OTP Request")
   .post(Config.app_url + Config.CREATE_OTP_URL)
    .header("x-via-device", "true")
    .body(StringBody("""{
        |"channelPartnerID": "${channelPartnerID}",
        |"mobileNumber": "${evg_phone_number}",
        |"country": "${country}",
        |"timestamp": "${getDateTime}"
        |}""".stripMargin)).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))

}
