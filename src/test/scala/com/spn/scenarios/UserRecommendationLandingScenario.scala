package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.UserRecommendationLandingRequest
import io.gatling.core.Predef.{scenario, _}

object UserRecommendationLandingScenario {

  val pageID_Landing = csv("data/pageid_Recommendation_Landing.csv").circular

  val scnuserRecommendationLandingScenario = scenario("User Recommendation Landing Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(pageID_Landing)
   .exec(UserRecommendationLandingRequest.userRecommendationLandingRequest)
//    .exec(session => {
//
//      println("Testing 50 k users processing time")
//      session
//    })
}
