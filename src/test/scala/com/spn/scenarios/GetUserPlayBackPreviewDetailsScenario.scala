package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.GetUserPlayBackPreviewDetailsRequest
import io.gatling.core.Predef.{scenario, _}

object GetUserPlayBackPreviewDetailsScenario{

//  val previewDetailsDataFeeder=csv("data/previewDetails.csv").circular

  val getUserPlayBackPreviewDetailsScenario =scenario("Get User PlayBack Preview Details Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.previewDetailsDataFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .exec(ApiSecurity.getToken)
    .exec(GetUserPlayBackPreviewDetailsRequest.PreviewDetails)
}
