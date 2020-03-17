package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.ContentDetailBundle
import io.gatling.core.Predef.{scenario, _}

object ContentDetailBundleScenario {
val bundleIdFeed = csv("data/bundleId.csv").circular

  val contentDetail_BundleScenario = scenario("Content Detail Bundle Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(bundleIdFeed)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .exec(ContentDetailBundle.ContentDetailBundle)
}