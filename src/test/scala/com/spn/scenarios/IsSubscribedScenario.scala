package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.common.CommonFeedFiles
import com.spn.requests.IsSubscribedRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object IsSubscribedScenario{



  val isSubscribedScenario =scenario("Is Subscribed Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .exec(IsSubscribedRequest.isSubscribed)
}
