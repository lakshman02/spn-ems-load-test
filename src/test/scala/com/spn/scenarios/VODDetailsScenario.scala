package com.spn.scenarios

import com.spn.requests.VODDetailsRequest
import com.spn.scenarios.GetULDScenario.{dataFeederChannel, dataFeederCluster, dataFeederLocale, dataFeederProperty, dataFeederTenant}
import io.gatling.core.Predef.{scenario, _}

object VODDetailsScenario {

 val dataFeederChannel = csv("data/channel.csv").circular
 val dataFeederCluster = csv("data/cluster.csv").circular
 val dataFeederLocale = csv("data/locale.csv").circular
 val dataFeederProperty = csv("data/property.csv").circular
 val dataFeederTenant = csv("data/tenant.csv").circular
 val contentFeeder = csv("data/contentID.csv").circular

  val vodDetailsScenario = scenario("VOD Details Mobile Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(contentFeeder)
    .exec(VODDetailsRequest.vodDetails)

}
