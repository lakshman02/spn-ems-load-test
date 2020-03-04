package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object DeleteSearchHistoryRequest {

  val DeleteSearchHistory = http("Post Delete Search History")
    .post (Config.app_url + Config.DELETE_SEARCH_HISTORY)
    .headers(Map ("Authorization" -> "${RESP_AUTH_TOKEN}",
      "x-via-device" -> "true"))

    .body(StringBody("""{
                       "delSearchParams": [
                        "king","football"
                       ]
                       }""".stripMargin)).asJson
    //Hardcoded body string
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
}
