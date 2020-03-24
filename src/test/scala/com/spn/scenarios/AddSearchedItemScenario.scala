package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.AddSearchedItemRequest
import io.gatling.core.Predef._

object AddSearchedItemScenario {

  val scnAddSearchedItem = scenario("Add Searched item")

    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.contentFeeder)
    .exec(ApiSecurity.getToken)
    .exec(AddSearchedItemRequest.AddSearchedItem)
}
