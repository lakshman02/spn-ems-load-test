package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.SubscriptionRemoveRequest
import io.gatling.core.Predef._

object SubscriptionRemoveScenario {

  val subscriptionRemoveScenario= scenario("Subscription Remove Scenario")

    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dataFeederServiceDetails)
    .feed(CommonFeedFiles.dateTimeFeeder)


    .exec(SubscriptionRemoveRequest.subscriptionRemove)
  //.exec (session => println(session) session)
}
