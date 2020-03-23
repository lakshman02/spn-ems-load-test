package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.{GetInitialConfigRequest, GetTokenRequest}
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._
import com.spn.config.Config

object GetInitialConfigScenario {

  val getInitialConfigScenario = scenario("Get Initial Config Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)

    .exec(ApiSecurity.getToken)
    .exec(GetInitialConfigRequest.getInitialConfig)
}