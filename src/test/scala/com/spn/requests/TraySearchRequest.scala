package com.spn.requests

import com.spn.common.Constants
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TraySearchRequest {

  val traySearchRequest = exec(http("Tray Search Request - ${filter_objectSubtype} - ${query}")
    .get(Config.app_url + Config.TRAY_SEARCH_URL)
    .queryParam("query", "${query}")
    .queryParam("filter_objectSubtype", "${filter_objectSubtype}")
    .queryParam("orderBy", "${orderBy}")
    .queryParam("sortOrder", "${sortOrder}")
    .queryParam("maxResults", "${maxResults}")
    .headers(Config.secHeader)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$.resultObj").saveAs(Constants.RESP_TRAY_SEARCH_RESPONSE))
  )
}