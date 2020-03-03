package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.NextRequest
import io.gatling.core.Predef.{scenario, _}

object NextScenario {

  val dataFeederContentID = csv("data/contentId.csv").circular

  val nextScenario = scenario("Next Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(dataFeederContentID)
    .exec(NextRequest.nextRequest)

}
