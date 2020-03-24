package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.TraySearchRequest
import io.gatling.core.Predef.{scenario, _}

object TraySearchScenario {

  val traySearchScenario = scenario("Tray Search Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.contentFeeder)
    .exec(ApiSecurity.getToken)
    .exec(TraySearchRequest.traySearchRequest)

}
