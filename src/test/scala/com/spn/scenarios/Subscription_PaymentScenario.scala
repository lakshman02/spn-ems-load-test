package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.Subscription_PaymentURL
import io.gatling.core.Predef.{scenario, _}

object Subscription_PaymentScenario  {

  val subscription_PaymentScenario= scenario("Subscription Payment Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.inputStagingDataFeeder)

    .exec(Subscription_PaymentURL.Subscription_Payment)
  //.exec (session => println(session) session)

}