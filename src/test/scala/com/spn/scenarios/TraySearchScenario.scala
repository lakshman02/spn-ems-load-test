package com.spn.scenarios

import com.spn.requests.TraySearchRequest
import io.gatling.core.Predef.{scenario,_}

object TraySearchScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val contentFeeder = csv("data/traySearchQueries.csv").circular

  val traySearchScenario = scenario("Tray Search Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(contentFeeder)
    .exec(TraySearchRequest.traySearchRequest)

}
