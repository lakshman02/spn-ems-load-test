package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object UserRecommendationLandingRequest {

  val userRecommendationLandingRequest = exec(http("User Recommendation Landing Request")
    .get(Config.app_url + Config.USER_RECOMMENDATION_LANDING_URL)
    .headers(Config.sentHeadersNew)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )

}

