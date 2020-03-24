package com.spn.requests

import com.spn.common.Constants
import io.gatling.core.Predef._
import com.spn.config.Config
import io.gatling.http.Predef._


object GetPageIdRequest {
  val PageId = exec(http("Get Page - ${pageSuffix}")

    .get(Config.app_url + Config.GET_PageID)
    .headers(Config.secHeader)
    .queryParam("from" , "${paginationFrom}")
    .queryParam("to" , "${paginationTo}")
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
