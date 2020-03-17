package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object DeleteAllSearchHistory {
  val DeleteAllSearchHistory = exec(http("Delete All Search History")

    .get(Config.app_url + Config.DELETE_ALL_SEARCH_HISTORY)
    .headers(Config.sentHeadersNew)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
