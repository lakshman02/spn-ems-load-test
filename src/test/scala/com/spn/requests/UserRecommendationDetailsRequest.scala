package com.spn.requests

import io.gatling.core.Predef._
import com.spn.config.Config
import io.gatling.http.Predef._

object UserRecommendationDetailsRequest {

  val userRecommendationDetailsRequest = exec(http("User Recommendation Details Request")
    .get(Config.app_url + Config.USER_RECOMMENDATION_DETAIL_URL)
    .headers(Config.sentHeadersNew)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )

}

