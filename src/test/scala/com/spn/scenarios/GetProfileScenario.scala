package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.GetProfileRequest
import io.gatling.core.Predef.scenario

object GetProfileScenario {


  val getProfileScenario =scenario("Get Profile Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsersUsingCircular)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .exec(ApiSecurity.getToken)
    .exec(GetProfileRequest.getProfile)
}
