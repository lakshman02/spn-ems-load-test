package com.spn.requests

import java.time.format.DateTimeFormatter
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import java.time.LocalDateTime

object CreateOTPRequest {

  val otpHeader = Map("x-via-device" -> "true")

  val createOTPRequest = exec(http("Create OTP Request")
   .post(Config.app_url + Config.CREATE_OTP_URL)
   .headers(otpHeader)
    .body(StringBody("""{
        |"channelPartnerID": "${channelPartnerID}",
        |"mobileNumber": "${mobileNumber}",
        |"country": "${country}",
        |"timestamp": "2020-01-02T10:14:45.872Z"
        |}""".stripMargin)).asJson
      .check(jsonPath("$.resultCode").is("OK"))
  )

}
