package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.ContentDetailBundle
import io.gatling.core.Predef.{scenario, _}

object ContentDetailBundleScenario {


  val contentDetail_BundleScenario = scenario("Content Detail Bundle Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .exec(ContentDetailBundle.ContentDetailBundle)
}