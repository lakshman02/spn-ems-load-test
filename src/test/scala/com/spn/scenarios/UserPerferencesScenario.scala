package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.UserPerferencesRequest

import io.gatling.core.Predef.{scenario, _}

object UserPerferencesScenario {

  val scnUserPerferences = scenario("User Perferences Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsersUsingCircular)
    .exec(UserPerferencesRequest.userPerferencesAPI)
}