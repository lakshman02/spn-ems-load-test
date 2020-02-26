package com.spn.scenarios

import com.spn.requests.ShowDetailRequest
import io.gatling.core.Predef.{scenario, _}

object ShowDetailScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard

  val showDetailScenario = scenario("Show Detail Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(userCredentials)
    .exec(ShowDetailRequest.showDetailRequest)
}