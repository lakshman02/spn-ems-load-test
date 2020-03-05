package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.GetSearchHistoryRequest
import io.gatling.core.Predef.{scenario, _}

object GetSearchHistoryScenario {

  val getSearchHistoryScenario = scenario("Get Search History Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsersUsingCircular)
    .exec(GetSearchHistoryRequest.getSearchHistory)
}
