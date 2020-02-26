package com.spn.scenarios
import io.gatling.core.Predef._

import com.spn.requests.EpisodeDetailRequest

object EpisodeIdDetailScenario {
  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederEpisodeId = csv("data/episodeid.csv")

  val scnEpisodeIdDetails = scenario("Get Episode Id Details ")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(dataFeederEpisodeId)
    .exec(EpisodeDetailRequest.Episode_Detail)

}
