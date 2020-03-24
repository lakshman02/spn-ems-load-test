package com.spn.requests

import com.spn.common.Constants
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetInitialConfigRequest {

  val getInitialConfig = exec(http("Get Initial Config Request")
    .get(Config.app_url + Config.URL_INITIAL_CONFIG)
    // TODO - add the security_token header here
    .headers(Config.secHeader)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    .check(jsonPath("$.resultObj").saveAs(Constants.RESP_INITIAL_CONFIG)) // TODO - Save complete JSON

    //    .check(jsonPath("$.resultObj.*.containers[*].actions[?(@.targetType == 'PAGE')].uri").findRandom.saveAs(Constants.RESP_RANDOM_PAGE_URL)) // TODO - Save complete JSON
    // .check(jsonPath("$.resultObj.containers[*].actions[?(@.targetType == 'PAGE')].uri").findAll.saveAs(Constants.RESP_RANDOM_PAGE_URL))
  )
}