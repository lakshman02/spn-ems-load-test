package com.spn.scenarios

import com.spn.requests.AccountSearchRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

//Account Search Scenario
object AccountSearchScenario {
  val dataFeederChannel = csv("data/channel.csv").random
  val dataFeederCluster = csv("data/cluster.csv").random
  val dataFeederLocale = csv("data/locale.csv").random
  val dataFeederProperty = csv("data/property.csv").random
  val dataFeederTenant = csv("data/tenant.csv").random
  val evergentLoginData = csv("data/evergent/evergent_data_simple_file.csv.gz").unzip.circular

  val accountSearchScenario =scenario("Account Search Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(evergentLoginData)
    .exec(AccountSearchRequest.accountSearch)
}
