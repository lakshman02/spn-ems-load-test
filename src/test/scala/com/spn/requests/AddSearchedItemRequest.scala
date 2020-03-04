package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object AddSearchedItemRequest {
  val AddSearchedItem = exec(http("Add Searched Item ")

    .get(Config.app_url + Config.ADDSEARCHEDITEM)
<<<<<<< HEAD
    .headers(Config.sentHeaders)
    .queryParam("searchedItem" , "king")
=======
      .headers(Map("Authorization" -> "${RESP_AUTH_TOKEN}",
        "x-via-device" -> "true"))
    .queryParam("searchedItem" , "${query}")

>>>>>>> eff9e06b088efab2c1f647602cbc395d33c3f120
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("success")))
}
