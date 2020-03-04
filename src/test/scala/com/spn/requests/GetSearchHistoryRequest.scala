package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetSearchHistoryRequest {

  val getSearchHistory = exec(http("Get Search History Request")
    .get(Config.app_url + Config.GET_SEARCH_HISTORY_URL)
    .headers(Config.sentHeaders)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
