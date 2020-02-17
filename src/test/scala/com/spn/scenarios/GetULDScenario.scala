package com.spn.scenarios

import com.spn.requests.GetULDRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object GetULDScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular

  val getULDScenario = scenario("Get ULD Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .exec(GetULDRequest.getULD)


}
