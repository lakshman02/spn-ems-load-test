package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.SubscriptionOrderStatusDateRequest
import io.gatling.core.Predef.{scenario, _}

object SubscriptionOrderStatusDateScenario{

  val dataFeederOrderStatusForSubscription=csv("data/orderStatus_for_subscription.csv").circular

  val subscriptionOrderStatusDateScenario =scenario("SubscriptionOrderStatusDate Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_DeleteEpgReminder)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .feed(dataFeederOrderStatusForSubscription)
    .exec(SubscriptionOrderStatusDateRequest.subscriptionOrderStatusDateRequest)
}
