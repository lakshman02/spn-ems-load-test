package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.DetailsForEpisodeMovieShowRequest
import io.gatling.core.Predef.{scenario, _}

object DetailsForEpisodeScenario {

  val dataFeederContentId = csv("data/newContentID.csv").random

  val detailsForEpisodeScenario = scenario("Details For Episode Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(dataFeederContentId)
    .exec(ApiSecurity.getToken)
    .exec(DetailsForEpisodeMovieShowRequest.detailsForEpisodeMovieShowRequest)
}
