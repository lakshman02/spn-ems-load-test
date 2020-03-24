package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TrayRecommendationCatchMediaRequest {

  val recommendationType = "catchmedia"

  val trayRecommendationCatchMediaRequest = exec(http("Tray Recommendation CatchMedia Request")
    .get(Config.app_url + Config.TRAY_RECOMMENDATION_URL)
    .queryParam("recommendationType", recommendationType)
    .queryParam("railType", "${railType}")
    .queryParam("objectSubType", "${filter_contentSubtype}")
    .headers(Config.secHeader)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}


