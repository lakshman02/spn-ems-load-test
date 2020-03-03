package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.PostSyncStateRequest
import io.gatling.core.Predef._

object PostSyncStateScenario {

  val postSyncStateScenario = scenario("Post Sync State Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(CommonFeedFiles.dataFeederServiceDetails)
    .feed(CommonFeedFiles.userAuth1KUsers)

    .exec(PostSyncStateRequest.postSyncStateRequest)
}
