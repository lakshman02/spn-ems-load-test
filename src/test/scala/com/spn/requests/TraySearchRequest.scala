package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TraySearchRequest {

  val traySearchRequest = exec(http("Tray Search Request")
    .get(Config.app_url + Config.TRAY_SEARCH_URL)
    .queryParam("query", "${query}")
    .queryParam("filter_objectSubtype", "${filter_objectSubtype}")
    .queryParam("orderBy", "${orderBy}")
    .queryParam("sortOrder", "${sortOrder}")
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}


