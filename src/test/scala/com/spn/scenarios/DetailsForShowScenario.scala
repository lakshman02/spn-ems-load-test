package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.DetailsForEpisodeMovieShowRequest
import io.gatling.core.Predef.{scenario, _}

object DetailsForShowScenario {

  val dataFeederContentId = csv("data/show_contentIds.csv").random

  val detailsForShowScenario = scenario("Details For Show Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(dataFeederContentId)
    .exec(ApiSecurity.getToken)
    .exec(session => session.set("details_type","Show"))
    .exec(DetailsForEpisodeMovieShowRequest.detailsForEpisodeMovieShowRequest)
}
