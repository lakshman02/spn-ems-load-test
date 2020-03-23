package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.PreviewAddRequest
import io.gatling.core.Predef.{scenario,_}

object PreviewAddScenario   {

  val DataFeederpreviewDetails=csv("data/previewDetails.csv").circular


  val previewAddScenario = scenario("Preview Add Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.dataFeederAssetID)
    .feed(DataFeederpreviewDetails)
    .exec(PreviewAddRequest.previewAddRequest)
}