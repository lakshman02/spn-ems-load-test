package com.spn.scenarios.journey

import com.spn.common.Constants
import com.spn.requests.{GetInitialConfigRequest, GetMenuRequest, GetPageIdRequest}
import io.gatling.core.Predef._
import io.gatling.jsonpath.JsonPath

import scala.concurrent.duration._
import scala.util.parsing.json.JSON
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
      val initialConfigResponse = session(Constants.RESP_INITIAL_CONFIG).as[String]
      println(s"\nResponse from Session ; body is : $initialConfigResponse")

//      val parsed = JsonPath.query("","")
//      println(s"\nResponse Parsed ; body is : $parsed")

      session

    })
    .exec(GetPageIdRequest.PageId)
//    .pause(500 milliseconds)

}
