package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object UserRecommendationRequest {

  val userRecommendationRequest = exec(http("User Recommendation  Request")
    .get(Config.app_url + Config.USER_RECOMMENDATION_URL)
    .headers(Config.sentHeadersNew)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
  )

}

