package com.spn.requests
import com.spn.config.Config
import com.spn.common.Constants
import io.gatling.core.Predef._
import io.gatling.http.Predef._

//TODO observation: duplicate of LoginRequest
object LoginWithMobileNumRequest {

  val LoginWithMobile = exec(http("User Login (mobile) Request")
    .post(Config.app_url + Config.Login_URL)
    .headers(Config.devSecHeader)
    .body(StringBody ("""{
             "mobileNumber": "${mobileNumber}",
              "password": "${password}",
               "rememberMe": true,
             "appClientId": "${appClientId}",
              "channelPartnerID":"${channelPartnerID}",
            "timestamp": "${getDateTime}",
            "deviceType":"${deviceType}",
            "serialNo": "${serialNo}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$..accessToken").saveAs(Constants.RESP_AUTH_TOKEN)))
}
