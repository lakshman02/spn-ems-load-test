package com.spn.requests

import io.gatling.core.Predef._
import com.spn.config.Config
import io.gatling.http.Predef._
import com.spn.common.Constants

object GetListRequest {

  val getUserListRequest = exec(http("Get My List Request")
    .get(Config.app_url + Config.GET_LIST)
    .headers(Config.sentHeaders)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$.resultObj").saveAs(Constants.RESP_GET_MY_LIST))

  )

}
