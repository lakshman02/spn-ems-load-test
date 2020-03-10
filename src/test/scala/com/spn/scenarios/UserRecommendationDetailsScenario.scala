package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.UserRecommendationDetailsRequest
import io.gatling.core.Predef.{scenario, _}

object UserRecommendationDetailsScenario {

  val contentIdData = csv("data/contentID.csv").circular

  val userRecommendationDetailsScenario = scenario("User Recommendation Details Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsersUsingCircular)
    .feed(contentIdData)
    .exec(UserRecommendationDetailsRequest.userRecommendationDetailsRequest)

}
