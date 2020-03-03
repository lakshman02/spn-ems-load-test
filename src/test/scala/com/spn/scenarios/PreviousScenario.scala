package com.spn.scenarios

import com.spn.requests.PreviousRequest
import io.gatling.core.Predef.{scenario, _}

object PreviousScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederContentid = csv("data/contentID.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val scnPreviousContent = scenario("Get Previous Content Details")

    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederContentid)
    .feed(userCredentials)
    .exec(PreviousRequest.Previous)
}
