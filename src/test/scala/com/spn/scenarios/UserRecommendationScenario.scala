package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.UserRecommendationRequest
import io.gatling.core.Predef.{scenario, _}

object UserRecommendationScenario {

  val dataFeederPageID = csv("data/pageid_userRecommendation.csv").circular

  val userRecommendationScenario = scenario("User Recommendation Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom)
    .feed(dataFeederPageID)
    .exec(ApiSecurity.getToken)
    .exec(UserRecommendationRequest.userRecommendationRequest)
}
