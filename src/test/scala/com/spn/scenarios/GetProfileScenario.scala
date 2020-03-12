package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.GetProfileRequest
import io.gatling.core.Predef.scenario

object GetProfileScenario {


  val getProfileScenario =scenario("Get Profile Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)

    .exec(GetProfileRequest.getProfile)
}
