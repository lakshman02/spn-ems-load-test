package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
object GetProduct {

  val GetProduct  = exec(http("Get Product Request")
    .get(Config.app_url + Config.GET_PRODUCTS_URL)
    .headers(Config.sentHeaders)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}