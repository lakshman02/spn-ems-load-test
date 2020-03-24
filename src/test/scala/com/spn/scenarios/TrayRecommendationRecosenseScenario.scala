package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.TrayRecommendationRecosenseRequest
import io.gatling.core.Predef.scenario

object TrayRecommendationRecosenseScenario {

  val trayRecommendationRecosenseScenario = scenario("Tray Recommendation Recosense Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.contentFeeder)
    .exec(ApiSecurity.getToken)
    .exec(TrayRecommendationRecosenseRequest.trayRecommendationRecosenseRequest)

}
