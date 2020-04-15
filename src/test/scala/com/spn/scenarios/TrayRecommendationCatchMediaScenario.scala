package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.TrayRecommendationCatchMediaRequest
import io.gatling.core.Predef.scenario

object TrayRecommendationCatchMediaScenario {

  val trayRecommendationCatchMediaScenario = scenario("Tray Recommendation CatchMedia Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.trayRailTypeFeeder)
    .feed(CommonFeedFiles.objectSubTypeFeeder)
    .exec(ApiSecurity.getToken)
    .exec(TrayRecommendationCatchMediaRequest.trayRecommendationCatchMediaRequest)

}
