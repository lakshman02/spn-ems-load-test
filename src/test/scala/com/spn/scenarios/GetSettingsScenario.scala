package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.GetSettingsRequest
import io.gatling.core.Predef.scenario

object GetSettingsScenario {

  val getSettingsScenario = scenario("Get settings Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_GetSetting)
    .exec(ApiSecurity.getToken)
    .exec(GetSettingsRequest.getSettings)
}
