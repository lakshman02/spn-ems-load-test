package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.BundleIdRequest
import io.gatling.core.Predef._

object BundleIdScenario {

  val dataFeederBundleId = csv("data/bundleId.csv").circular

  val scnbundle = scenario("Get Bundle Id Details")

    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(dataFeederBundleId)
    .exec(BundleIdRequest.BundleId)
}
