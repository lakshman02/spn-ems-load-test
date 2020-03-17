package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.NextRequest
import io.gatling.core.Predef.{scenario, _}

object NextScenario {

  val dataFeederContentID = csv("data/contentID.csv").circular

  val nextScenario = scenario("Next Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(dataFeederContentID)
    .exec(NextRequest.nextRequest)

}
