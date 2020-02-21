package com.spn.scenarios

import io.gatling.core.Predef.scenario
import com.spn.requests.GetPageIdRequest
import com.spn.scenarios.CreateOTPScenario.{dataFeederChannel, dataFeederCluster, dataFeederLocale, dataFeederProperty, dataFeederTenant}
import io.gatling.core.Predef._

import scala.concurrent.duration._

object GetPageIdScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular


  val PageId = scenario("Get Page Id")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)

    .exec(GetPageIdRequest.PageId)
//    .pause(500 milliseconds)
}
