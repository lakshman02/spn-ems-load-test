package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.PreviousRequest
import io.gatling.core.Predef.{scenario, _}

object PreviousScenario {

//  val previousContentidDataFeeder = csv("data/previous_data.csv").circular

  val scnPreviousContent = scenario("Get Previous Content Details")

    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.previousContentidDataFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .exec(ApiSecurity.getToken)
    .exec(PreviousRequest.Previous)
}
