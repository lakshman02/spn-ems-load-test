package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object TrayRecommendationRecosenseRequest {

  val recommendationType = "recosense"

  val trayRecommendationRecosenseRequest = exec(http("Tray Recommendation Recosense Request")
    .get(Config.app_url + Config.TRAY_RECOMMENDATION_URL)
    .queryParam("recommendationType", recommendationType)
    .queryParam("railType", "${railType}")
    .queryParam("objectSubType", "${filter_objectSubtype}")
    .headers(Config.secHeader)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )
}
