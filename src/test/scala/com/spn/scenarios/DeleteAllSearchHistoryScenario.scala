package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.DeleteAllSearchHistory
import io.gatling.core.Predef.scenario

object DeleteAllSearchHistoryScenario {


  val scnDeleteAllSearchHistory =scenario("Delete All Search History")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .exec(DeleteAllSearchHistory.DeleteAllSearchHistory)
}
