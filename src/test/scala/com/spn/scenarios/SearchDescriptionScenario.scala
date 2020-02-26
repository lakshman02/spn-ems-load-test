package com.spn.scenarios

import com.spn.requests.SearchDescriptionRequest
import io.gatling.core.Predef.{scenario,_}

object SearchDescriptionScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val contentFeeder = csv("data/traySearchQueries.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.circular

  val searchDescriptionScenario = scenario("Search Description Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(contentFeeder)
    .feed(userCredentials)
    .exec(SearchDescriptionRequest.searchDescriptionRequest)

}


