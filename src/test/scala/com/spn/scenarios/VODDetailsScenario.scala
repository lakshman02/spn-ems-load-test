package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.VODDetailsRequest
import io.gatling.core.Predef.{scenario, _}

object VODDetailsScenario {


 val contentFeeder = csv("data/contentID.csv").circular

  val vodDetailsScenario = scenario("VOD Details Mobile Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(contentFeeder)
    .exec(VODDetailsRequest.vodDetails)

}
