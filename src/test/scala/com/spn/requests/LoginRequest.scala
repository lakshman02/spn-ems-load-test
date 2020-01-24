package com.spn.requests

  import com.spn.config.Config
  import io.gatling.core.Predef._
  import io.gatling.http.Predef._
  object LoginRequest {

    val sentHeaders = Map("x-via-device" -> "true")
    val LoginRequest = exec(http("USER LOGIN ")
      .post(Config.app_url + Config.Login_URL)
      .headers(sentHeaders)
      .body(StringBody ("""{
             "mobileNumber": "${mobileNumber}",
              "password": "${password}",
               "rememberMe": true,
             "appClientId": "${appClientId}",
              "channelPartnerID":"MSMIND",
            "timestamp": "2020-01-03T05:22:49.959Z",
            "deviceType":"${deviceType}",
            "serialNo": ""
        }""")).asJson
    .check(jsonPath("$.resultCode").is("OK")))
}