package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.MovieDetailRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object MovieDetailScenario {

  val movieDetailData = csv("data/movieId.csv").circular

  val movieDetailScenario =scenario("Movie Detail Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(movieDetailData)
    .exec(MovieDetailRequest.movieDetail)
}
