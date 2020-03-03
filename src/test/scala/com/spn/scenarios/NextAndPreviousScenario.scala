package com.spn.scenarios

import com.spn.requests.NextAndPreviousRequest
import io.gatling.core.Predef.{scenario,_}

object NextAndPreviousScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val contentFeeder = csv("data/contentId.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.circular

  val nextAndPreviousScenario = scenario("Next and Previous Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(contentFeeder)
    .feed(userCredentials)
    .exec(NextAndPreviousRequest.nextAndPreviousRequest)

}

