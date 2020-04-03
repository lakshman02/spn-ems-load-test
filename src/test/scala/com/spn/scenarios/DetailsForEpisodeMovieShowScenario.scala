package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.DetailsForEpisodeMovieShowRequest
import io.gatling.core.Predef.{scenario, _}

object DetailsForEpisodeMovieShowScenario {

  val dataFeederContentId = csv("data/newContentID.csv").circular

  val detailsForEpisodeMovieShowScenario = scenario(" DetailsForEpisodeMovieShow Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuthForScenarioIndividualTestingUsersUsingRandom)
    .feed(dataFeederContentId)
    .exec(ApiSecurity.getToken)
    .exec(DetailsForEpisodeMovieShowRequest.detailsForEpisodeMovieShowRequest)

}
