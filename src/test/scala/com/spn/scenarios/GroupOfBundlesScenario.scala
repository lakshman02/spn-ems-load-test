package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.GroupOfBundlesRequest
import io.gatling.core.Predef.{scenario, _}

object GroupOfBundlesScenario{


  val dataFeederBundleId = csv("data/groupBundleId.csv").circular

  val groupOfBundlesScenario =scenario("Group Of Bundles Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(dataFeederBundleId)
    .exec(GroupOfBundlesRequest.groupOfBundles)
}
