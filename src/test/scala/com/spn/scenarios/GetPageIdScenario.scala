package com.spn.scenarios

import com.spn.common.Constants
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._
import com.spn.requests.{GetMenuRequest, GetPageIdRequest}

import scala.concurrent.duration._

object GetPageIdScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederPageId = csv("data/pageid.csv").circular

  val PageId = scenario("Get Page Id")

    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederPageId)

    .exec(GetPageIdRequest.PageId)

//    .pause(500 milliseconds)
}
