package com.spn.requests

import com.spn.common.Constants
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TraySearchVODRequest {

  val traySearchVODRequest = exec(http("Tray Search VOD Request - ${filter_contentSubtype}")
    .get(Config.app_url + Config.TRAY_SEARCH_VOD_URL)
    .queryParam("filter_contentSubtype", "${filter_contentSubtype}")
    .queryParam("sortOrder", "${sortOrder}")
    .headers(Config.secHeader)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$.resultObj").saveAs(Constants.RESP_TRAY_SEARCH_VOD_RESPONSE))
  )
}
