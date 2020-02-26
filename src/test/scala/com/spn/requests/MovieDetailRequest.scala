package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object MovieDetailRequest {
  val movieDetail= exec(http("Movie Detail Request")
    .get(Config.app_url + Config.MOVIE_DETAIL_URL)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))

  )
}
