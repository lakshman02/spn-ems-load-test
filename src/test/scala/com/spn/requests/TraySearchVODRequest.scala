package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TraySearchVODRequest {

  val traySearchVODRequest = exec(http("Tray Search VOD Request")
    .get(Config.app_url + Config.TRAY_SEARCH_VOD_URL)
    .queryParam("filter_contentSubtype", "${filter_contentSubtype}")
    .queryParam("sortOrder", "${sortOrder}")
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
