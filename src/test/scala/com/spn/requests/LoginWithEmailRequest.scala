package com.spn.requests

import com.spn.config.Config
import com.spn.common.Constants
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
            "timestamp": "${getDateTime}",
            "deviceType":"${deviceType}",
            "serialNo": "${serialNo}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$..accessToken").saveAs(Constants.RESP_AUTH_TOKEN)))

}
