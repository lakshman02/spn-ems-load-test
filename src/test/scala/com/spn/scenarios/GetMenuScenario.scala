package com.spn.scenarios

import com.spn.common.Constants
import com.spn.requests.GetMenuRequest
import com.spn.scenarios.CreateOTPScenario.{dataFeederChannel, dataFeederCluster, dataFeederLocale, dataFeederProperty, dataFeederTenant}
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

//"Get Menu" scenario
object GetMenuScenario{

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val getMenuScenario =scenario("Get Menu Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .exec(GetMenuRequest.getMenu)
//    .exec(session => {
//       val channel = session("channel").as[String]
//       val response = session(Constants.RESP_MENU).as[String]
//       println(s"\nMENU Response from Session for channel : $channel ; body is : $response")
//      session
//    })
}
