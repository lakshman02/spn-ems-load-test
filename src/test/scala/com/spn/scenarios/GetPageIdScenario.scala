package com.spn.scenarios

import io.gatling.core.Predef.scenario
import com.spn.requests.GetPageIdRequest
import com.spn.scenarios.CreateOTPScenario.{dataFeederChannel, dataFeederCluster, dataFeederLocale, dataFeederProperty, dataFeederTenant}
import io.gatling.core.Predef._

import scala.concurrent.duration._

object GetPageIdScenario {

  val dataFeederChannel = csv("data/platform.csv").circular

  val PageId = scenario("Get Page Id")
    .feed(dataFeederTenant)
    .exec(GetPageIdRequest.PageId)
//    .pause(500 milliseconds)
}
