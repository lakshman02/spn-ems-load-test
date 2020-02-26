package com.spn.scenarios

import com.spn.requests.ProrateAmountRequest
import io.gatling.core.Predef.{scenario, _}

object ProrateAmountScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val serviceIdData = csv("data/serviceId.csv").circular
  val authFeeder = csv("data/LoginID.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random


  val prorateAmountScenario = scenario("Prorate Amount Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .feed(serviceIdData)
    .feed(authFeeder)
    .feed(userCredentials)

    .exec(ProrateAmountRequest.prorateAmountRequest)

}
