package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.GetSyncStateRequest
import io.gatling.core.Predef.{scenario, _}

object GetSyncStateScenario{


  val getSyncStateScenario =scenario("Get Sync state Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .exec(GetSyncStateRequest.getSyncState)
}
