package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object VideoUrlRequest {
  val sentHeaders = Map(
    "app_version" -> "1.0",
    "build_number" -> "1.0"
  )

  val videoUrl= exec(http("Video Url Request")
    .get(Config.app_url + Config.VIDEO_URL)
    .headers(sentHeaders)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
    )
}
