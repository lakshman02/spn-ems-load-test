package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.GetULDRequest
import io.gatling.core.Predef.scenario

object GetULDScenario {

  val getULDScenario = scenario("Get ULD Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .exec(ApiSecurity.getToken)
    .exec(GetULDRequest.getULD)

}
