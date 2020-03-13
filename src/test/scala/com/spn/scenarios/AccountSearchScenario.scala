package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.AccountSearchRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

//Account Search Scenario
object AccountSearchScenario {
  val accountSearchScenario =scenario("Account Search Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.inputStagingDataFeeder)

    .exec(AccountSearchRequest.accountSearch)
}
