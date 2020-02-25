package com.spn.scenarios

import com.spn.requests.GetLAUrlRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object GetLAUrlScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val deviceFeeder = csv("data/device_details.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard


  val getLAUrlScenario = scenario("Get LA URL Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(userCredentials)
    .feed(deviceFeeder)
    .exec(GetLAUrlRequest.getLaUrl)
}



