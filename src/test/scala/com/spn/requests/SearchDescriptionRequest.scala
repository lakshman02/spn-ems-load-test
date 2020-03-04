package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SearchDescriptionRequest {

  val searchDescriptionRequest = exec(http("Search Description Request")
    .get(Config.app_url + Config.SEARCH_DESCRIPTION_URL)
    .headers(Config.sentHeaders)
    .queryParam("query", "king")
    .queryParam("filter_contentType", "VOD")
    .queryParam("maxResults", "20")
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}


