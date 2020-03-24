package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object VideoUrlRequest {

  val videoUrl= exec(http("Video Url Request")
    .get(Config.app_url + Config.VIDEO_URL)
    .headers(Config.secHeader)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    )
}
