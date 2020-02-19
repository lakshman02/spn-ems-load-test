package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.AllSubscriptionsRequest
import com.spn.scenarios.IsCustomerEligibleForFreeTrialScenario.{dataFeederChannel, dataFeederCluster, dataFeederLocale, dataFeederProperty, dataFeederTenant, loginEmailData, userCredentials}
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object AllSubscriptionsScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val loginEmailData = csv("data/LoginID.csv").circular
  val userCredentials = csv("data/evergent/evergent_data_1.csv.gz").unzip.shard


  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val getAllSubscriptionsScenario = scenario("All Subscriptions Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .feed(userCredentials)
    .feed(loginEmailData)
    .exec(AllSubscriptionsRequest.getAllSubscriptions)
}
