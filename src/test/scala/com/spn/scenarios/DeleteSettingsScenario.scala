package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import io.gatling.core.Predef._

import com.spn.requests.DeleteSettingsRequest

object DeleteSettingsScenario {
  val scnDeleteSetting = scenario(" Delete Settings")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_DeleteSetting)
    .exec(DeleteSettingsRequest.Delete_Settings)
}
