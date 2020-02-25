package com.spn.scenarios

import com.spn.requests.GetSyncStateRequest
import io.gatling.core.Predef.{scenario, _}

object GetSyncStateScenario{

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val usersWithAuthtokenDataFeeder = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard

  val getSyncStateScenario =scenario("Get Sync state Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(usersWithAuthtokenDataFeeder)
    .exec(GetSyncStateRequest.getSyncState)
}
