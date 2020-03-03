package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.ActiveSubscription
import io.gatling.core.Predef.{scenario, _}

object ActiveSubscriptionScenario   {

  val bodydatafeeder = csv("data/LoginID.csv").circular

  val activeSubscriptionScenario = scenario("Active Subscription Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(bodydatafeeder)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .exec(ActiveSubscription.ActiveSubscription)
  //.exec (session => println(session) session)

}