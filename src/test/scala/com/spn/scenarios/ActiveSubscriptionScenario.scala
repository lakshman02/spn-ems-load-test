package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
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
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .exec(ApiSecurity.getToken)
    .exec(ActiveSubscription.ActiveSubscription)
  //.exec (session => println(session) session)

}