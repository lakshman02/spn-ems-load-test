package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object SearchDescriptionRequest {

  val searchDescriptionRequest = exec(http("Search Description Request")
    .get(Config.app_url + Config.SEARCH_DESCRIPTION_URL)
    .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}",
      "x-via-device" -> "true"))
    .queryParam("query", "${query}")
    .queryParam("filter_contentType", "${filter_contentType}")
    .queryParam("maxResults", "${maxResults}")
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}


