package com.spn.scenarios

import com.spn.common.{CommonFeedFiles, Constants}
import com.spn.requests.GetTokenRequest
import io.gatling.core.Predef.{scenario, _}

object GetTokenScenario {
  val getTokenScenario =scenario("Get Token Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .exec(GetTokenRequest.getToken)

}
