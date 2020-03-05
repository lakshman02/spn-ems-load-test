package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.SearchDescriptionRequest
import io.gatling.core.Predef.{scenario, _}

object SearchDescriptionScenario {

  val searchDescriptionScenario = scenario("Search Description Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsersUsingCircular)
    .feed(CommonFeedFiles.contentFeeder)
    .exec(SearchDescriptionRequest.searchDescriptionRequest)

}


