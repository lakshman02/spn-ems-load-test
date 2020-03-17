package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.VideoUrlRequest
import io.gatling.core.Predef.{scenario, _}

object VideoUrlScenario {

  val contentID = csv("data/contentID.csv").circular

  val videoUrlScenario =scenario("Video Url Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(contentID)
    .exec(VideoUrlRequest.videoUrl)
}
