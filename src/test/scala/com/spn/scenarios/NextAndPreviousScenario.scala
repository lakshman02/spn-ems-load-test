package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.NextAndPreviousRequest
import io.gatling.core.Predef.{scenario, _}

object NextAndPreviousScenario {

  val contentFeeder = csv("data/next_nextAndPrevious_data.csv").circular

  val nextAndPreviousScenario = scenario("Next and Previous Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(contentFeeder)
    .exec(ApiSecurity.getToken)
    .exec(NextAndPreviousRequest.nextAndPreviousRequest)

}

