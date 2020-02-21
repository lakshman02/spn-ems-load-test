package com.spn.scenarios

import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._
import com.spn.requests.PostSubscriptionHistoryRequest
import com.spn.scenarios.CreateOTPScenario.{dataFeederChannel, dataFeederCluster, dataFeederLocale, dataFeederProperty, dataFeederTenant}

object PostSubscriptionHistoryScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val bodydatafeeder = csv("data/LoginID.csv")
  val userAuthFeeder = csv ("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard

  val SubscriptionHistory = scenario("Post Subscription History")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(bodydatafeeder)
    .feed(userAuthFeeder)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .exec(PostSubscriptionHistoryRequest.subscriptionHistory)
}
