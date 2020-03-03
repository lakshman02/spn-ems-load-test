package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.NextAndPreviousRequest
import io.gatling.core.Predef.{scenario, _}

object NextAndPreviousScenario {

  val contentFeeder = csv("data/contentID.csv").circular

  val nextAndPreviousScenario = scenario("Next and Previous Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(contentFeeder)
    .exec(NextAndPreviousRequest.nextAndPreviousRequest)

}

