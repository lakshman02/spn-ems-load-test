package com.spn.scenarios

import com.spn.requests.GetProduct
import io.gatling.core.Predef.{scenario, _}

object GetProductScenario   {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederOtpRequirements = csv("data/LoginID.csv").circular
  val dataSalesFeeder = csv("data/payment_details.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val getProductScenario = scenario("Get Product Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederOtpRequirements)
    .feed(dataSalesFeeder)
    .feed(userCredentials)

    .exec(GetProduct .GetProduct)
  //.exec (session => println(session) session)

}