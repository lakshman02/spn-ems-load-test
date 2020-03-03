package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.ActiveSubscription
import io.gatling.core.Predef.{scenario, _}

object ActiveSubscriptionScenario   {


  val activeSubscriptionScenario = scenario("Active Subscription Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .exec(ActiveSubscription.ActiveSubscription)
  //.exec (session => println(session) session)

}