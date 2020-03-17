package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.UpgradablePlansRequest
import io.gatling.core.Predef.{scenario, _}

object UpgradablePlansScenario{

  val upgradablePlansScenario =scenario("Upgradable Plans Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .exec(UpgradablePlansRequest.upgradablePlans)
}
