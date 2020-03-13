package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.ShowDetailRequest
import io.gatling.core.Predef.{scenario, _}

object ShowDetailScenario {

  val showDetailScenario = scenario("Show Detail Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .exec(ShowDetailRequest.showDetailRequest)
}