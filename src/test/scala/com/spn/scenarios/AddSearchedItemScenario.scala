package com.spn.scenarios

import com.spn.requests.AddSearchedItemRequest
import io.gatling.core.Predef._

object AddSearchedItemScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederSearchvalue =csv ("data/traySearchQueries.csv").circular
  val dataFeederAuthToken = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val scnAddSearchedItem = scenario("Add Searched item")

    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederAuthToken)
    .feed(dataFeederSearchvalue)
    .exec(AddSearchedItemRequest.AddSearchedItem)
}
