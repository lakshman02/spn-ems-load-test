package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

//"Get Menu" Request
object GetMenuRequest {
  val getMenu= exec(http("Get Menu Request")
    .get(Config.app_url + Config.GET_MENU_URL)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
