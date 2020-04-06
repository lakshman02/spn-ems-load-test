package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import com.spn.common.Constants

object DetailsForEpisodeMovieShowRequest {

  val detailsForEpisodeMovieShowRequest= exec(http("Details For Episode Or Movie Or Show Request")
    .get(Config.app_url + Config.DETAILS_FOR_EPISODE_MOVIE_SHOW)
    .headers(Config.headerWithoutAuth)
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK"))
      .check(jsonPath("$.resultObj").saveAs(Constants.RESP_DETAIL_PAGE_RESPONSE))
  )
}
