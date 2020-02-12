package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
object LoginWithEmailRequest {

  val sentHeaders = Map("x-via-device" -> "true")
  val LoginWithEmail = exec(http("User Login (email) Request")
    .post(Config.app_url + Config.Login_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
             "email": "${email}",
              "password": "${password}",
               "rememberMe": true,
             "appClientId": "${appClientId}",
              "channelPartnerID":"MSMIND",
            "timestamp": "2020-01-03T05:22:49.959Z",
            "deviceType":"${deviceType}",
            "serialNo": ""
        }""")).asJson
    .check(jsonPath("$.accessToken").saveAs("authToken"))
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))

}
