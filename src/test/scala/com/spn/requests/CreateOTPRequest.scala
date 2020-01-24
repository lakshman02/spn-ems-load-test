package com.spn.requests

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
        |"timestamp": "${getDateTime}"
        |}""".stripMargin)).asJson
      .check(jsonPath("$.resultCode").is("OK"))
  )

}
