package com.spn.scenarios.journey

import com.spn.common.Constants
import com.spn.requests.{GetInitialConfigRequest, GetMenuRequest, GetPageIdRequest}
import io.gatling.core.Predef._
object UserJourneyLandingPage {

  val dataFeederChannel = csv("data/channel.csv").random
  val dataFeederCluster = csv("data/cluster.csv").random
  val dataFeederLocale = csv("data/locale.csv").random
  val dataFeederProperty = csv("data/property.csv").random
  val dataFeederTenant = csv("data/tenant.csv").random

  val LandingPage = scenario("User Journey - Landing Page")

    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)

    .exec(GetInitialConfigRequest.getInitialConfig)
//    .pause(500 milliseconds)

    .exec(GetMenuRequest.getMenu)
//    .pause(500 milliseconds)

    .exec(session => {
      val initialConfigResponse = session(Constants.RESP_RANDOM_PAGE_URL).as[String]
      println(s"\nRESP_RANDOM_PAGE_URL : $initialConfigResponse")
      session

    })
    .exec(GetPageIdRequest.PageId)
//    .pause(500 milliseconds)

}
