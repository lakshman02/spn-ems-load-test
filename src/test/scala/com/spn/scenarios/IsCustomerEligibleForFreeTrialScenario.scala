package com.spn.scenarios

import com.spn.requests.IsCustomerEligibleForFreeTrialRequest
import io.gatling.core.Predef.{scenario, _}

object IsCustomerEligibleForFreeTrialScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val loginEmailData = csv("data/LoginID.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random



  val checkCustomerEligibleForFreeTrial = scenario("Check if the user is eligible for free trial Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .feed(userCredentials)
    .feed(loginEmailData)
    .exec(IsCustomerEligibleForFreeTrialRequest.checkCustomerEligibleForFreeTrial)

}
