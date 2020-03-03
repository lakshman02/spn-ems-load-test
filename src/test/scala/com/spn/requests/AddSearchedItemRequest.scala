package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object AddSearchedItemRequest {
  val AddSearchedItem = exec(http("Add Searched Item ")

    .get(Config.app_url + Config.ADDSEARCHEDITEM)
      .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}",
        "x-via-device" -> "true"))
    .queryParam("searchedItem" , "king")

    .check(status is 200)
    .check(jsonPath("$.resultCode").is("success")))
}