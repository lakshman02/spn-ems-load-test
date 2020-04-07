package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.DetailsForEpisodeMovieShowRequest
import io.gatling.core.Predef.{scenario, _}

object DetailsForMovieScenario {

  val dataFeederContentId = csv("data/movie_contentIds.csv").random

  val detailsForMovieScenario = scenario("Details For Movie Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(dataFeederContentId)
    .exec(ApiSecurity.getToken)
    .exec(session => session.set("details_type","Movie"))
    .exec(DetailsForEpisodeMovieShowRequest.detailsForEpisodeMovieShowRequest)
}
