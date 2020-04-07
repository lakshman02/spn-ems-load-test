package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.NextAndPreviousRequest
import io.gatling.core.Predef.{scenario, _}

object NextAndPreviousScenario {

//  val dataFeederContentID = csv("data/next_nextAndPrevious_data.csv").circular

  val nextAndPreviousScenario = scenario("Next and Previous Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom) // TODO - is this needed
    .feed(CommonFeedFiles.NextPreviousContentidDataFeeder)
    .exec(ApiSecurity.getToken)
    .exec(NextAndPreviousRequest.nextAndPreviousRequest)

}

