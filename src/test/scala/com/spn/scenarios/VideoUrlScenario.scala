package com.spn.scenarios

import com.spn.requests.VideoUrlRequest
import io.gatling.core.Predef.{scenario, _}

object VideoUrlScenario {
  val dataFeederChannel = csv("data/channel.csv").random
  val dataFeederCluster = csv("data/cluster.csv").random
  val dataFeederLocale = csv("data/locale.csv").random
  val dataFeederProperty = csv("data/property.csv").random
  val dataFeederTenant = csv("data/tenant.csv").random
  val contentID = csv("data/contentID.csv").random

  val videoUrlScenario =scenario("Video Url Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(contentID)
    .exec(VideoUrlRequest.videoUrl)
}
