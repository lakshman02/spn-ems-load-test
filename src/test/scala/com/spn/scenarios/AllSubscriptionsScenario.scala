package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.AllSubscriptionsRequest
import io.gatling.core.Predef.{scenario, _}

object AllSubscriptionsScenario {

  val getAllSubscriptionsScenario = scenario("All Subscriptions Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.dataFeederSingleChannelpartnerid)
    .exec(ApiSecurity.getToken)
    .exec(AllSubscriptionsRequest.getAllSubscriptions)
}
