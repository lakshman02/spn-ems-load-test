package com.spn.scenarios

import com.spn.requests.GetSearchHistoryRequest
import io.gatling.core.Predef.{scenario, _}

object GetSearchHistoryScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val userAuthFeeder = csv ("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val getSearchHistoryScenario = scenario("Get Search History Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(userAuthFeeder)
    .exec(GetSearchHistoryRequest.getSearchHistory)
}
