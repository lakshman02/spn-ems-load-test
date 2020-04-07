package com.spn.requests

import com.spn.common.Constants
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TraySearchRequest {

  val traySearchRequest = exec(http("Tray Search Request")
    .get(Config.app_url + Config.TRAY_SEARCH_URL)
    .queryParam("query", "${query}")
    .queryParam("filter_objectSubtype", "${filter_objectSubtype}") //todo this is not present in confluence url
    .queryParam("orderBy", "${orderBy}") //todo this is not present in confluence url
    .queryParam("sortOrder", "${sortOrder}") //todo this is not present in confluence url
    .queryParam("maxResults", "${maxResults}") //todo this is not present in confluence url
    .headers(Config.secHeader)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$.resultObj").saveAs(Constants.RESP_TRAY_SEARCH_RESPONSE))
  )
}