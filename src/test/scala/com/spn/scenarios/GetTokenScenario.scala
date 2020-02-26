package com.spn.scenarios

import com.spn.requests.GetTokenRequest
import io.gatling.core.Predef.{scenario, _}

object GetTokenScenario {
  val dataFeederChannel = csv("data/channel.csv").random
  val dataFeederCluster = csv("data/cluster.csv").random
  val dataFeederLocale = csv("data/locale.csv").random
  val dataFeederProperty = csv("data/property.csv").random
  val dataFeederTenant = csv("data/tenant.csv").random
  val usersWithAuthtokenDataFeeder = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val getTokenScenario =scenario("Get Token Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(usersWithAuthtokenDataFeeder)
    .exec(GetTokenRequest.getToken)
}
