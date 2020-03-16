package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.DeleteListRequest
import io.gatling.core.Predef.{scenario, _}

object DeleteListScenario {

  val deleteListScenario =scenario("Delete List Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dataFeederAssetID)
    .exec(DeleteListRequest.deleteList)
}
