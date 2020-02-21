package com.spn.scenarios

import com.spn.requests.Subscription_PaymentURL
import com.spn.scenarios.CreateOTPScenario.userCredentials
import io.gatling.core.Predef.{scenario, _}

object Subscription_PaymentScenario  {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.circular

  val subscription_PaymentScenario= scenario("Subscription Payment Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(userCredentials)

    .exec(Subscription_PaymentURL.Subscription_Payment)
  //.exec (session => println(session) session)

}