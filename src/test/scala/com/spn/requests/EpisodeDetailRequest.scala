package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._


object EpisodeDetailRequest {

  val Episode_Detail = exec(http("Get Episode Detail")
    .get(Config.app_url + Config.EPISODE_DETAILS)
    .headers(Config.secHeader)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
