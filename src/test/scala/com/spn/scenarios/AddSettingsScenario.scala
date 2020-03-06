package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.AddSettingsRequest
import io.gatling.core.Predef.{scenario, _}

object AddSettingsScenario   {

  val addSettingsScenario = scenario("Add Settings Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .exec(AddSettingsRequest.addSettings)
}