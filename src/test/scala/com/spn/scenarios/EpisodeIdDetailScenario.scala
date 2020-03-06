package com.spn.scenarios
import com.spn.common.CommonFeedFiles
import io.gatling.core.Predef._
import com.spn.requests.EpisodeDetailRequest

object EpisodeIdDetailScenario {
  val dataFeederEpisodeId = csv("data/episodeid.csv").circular

  val scnEpisodeIdDetails = scenario("Get Episode Details")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(dataFeederEpisodeId)
    .exec(EpisodeDetailRequest.Episode_Detail)

}
