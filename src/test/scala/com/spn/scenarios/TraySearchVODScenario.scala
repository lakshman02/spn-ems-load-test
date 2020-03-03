package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.TraySearchVODRequest
import io.gatling.core.Predef.{scenario, _}

object TraySearchVODScenario {

  val traySearchVODScenario = scenario("Tray Search VOD Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.contentFeeder)
    .exec(TraySearchVODRequest.traySearchVODRequest)

}
