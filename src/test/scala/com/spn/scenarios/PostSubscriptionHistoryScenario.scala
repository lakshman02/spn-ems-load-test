package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.PostSubscriptionHistoryRequest
import io.gatling.core.Predef.{scenario, _}

object PostSubscriptionHistoryScenario {


  val SubscriptionHistory = scenario(" Subscription History")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(bodydatafeeder)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .exec(PostSubscriptionHistoryRequest.subscriptionHistory)
}
