package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.LogoutRequest
import io.gatling.core.Predef.{scenario, _}

object LogoutScenario {

  val logoutScenario = scenario("Logout Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
   .exec(LogoutRequest.logoutRequest)

}
