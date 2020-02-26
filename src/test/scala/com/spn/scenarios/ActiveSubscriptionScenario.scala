package com.spn.scenarios

import com.spn.requests.ActiveSubscription
import io.gatling.core.Predef.{scenario, _}
object ActiveSubscriptionScenario   {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val bodydatafeeder = csv("data/LoginID.csv").circular
  val userAuthFeeder = csv ("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val activeSubscriptionScenario = scenario("Active Subscription Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(bodydatafeeder)
    .feed(userAuthFeeder)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .exec(ActiveSubscription.ActiveSubscription)
  //.exec (session => println(session) session)

}