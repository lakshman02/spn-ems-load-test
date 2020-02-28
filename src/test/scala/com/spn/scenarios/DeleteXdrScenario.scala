package com.spn.scenarios

import com.spn.requests.DeleteXdrRequest
import io.gatling.core.Predef.{scenario, _}

object DeleteXdrScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederContentId = csv("data/contentID.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val deleteXdrScenario = scenario("Delete XDR Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederContentId)
    .feed(userCredentials)
    .exec(DeleteXdrRequest.deleteXdrRequest)

}
