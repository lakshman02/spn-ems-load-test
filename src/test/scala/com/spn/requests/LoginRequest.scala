package com.spn.requests

import com.spn.common.Constants
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object LoginRequest {

  val sentHeaders = Map("x-via-device" -> "true")
  val LoginRequest = exec(http("User Login (mobile) Request")
    .post(Config.app_url + Config.Login_URL)
    .headers(sentHeaders)
    .body(StringBody(
      """{
             "mobileNumber": "${evg_phone_number}",
              "password": "${evg_password}",
               "rememberMe": true,
             "appClientId": "${appClientId}",
              "channelPartnerID":"${channelPartnerID}",
            "timestamp": "${getDateTime}",
            "deviceType":"${deviceType}",
            "serialNo": "${serialNo}"
        }""")).asJson
    .check(status.is(200))
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$..cpCustomerID").is("1"))
    .check(jsonPath("$..accessToken").saveAs(Constants.RESP_AUTH_TOKEN))
  )
}