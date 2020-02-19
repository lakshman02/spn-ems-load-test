package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

//Account Search Request
object AccountSearchRequest {
  val accountSearch= exec(http("Account Search Request")
    .post(Config.app_url + Config.ACCOUNT_SEARCH_URL)
    .body(StringBody ("""{
             "mobileNumber": "${evg_phone_number}",
              "email": "${evg_email}",
              "channelPartnerID": "{{channelPartnerID}}"
        }""")).asJson
    .check(jsonPath("$.resultCode").is("OK"))
    .check(status is 200)
  )
}
