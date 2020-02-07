package com.spn.scenarios

import io.gatling.core.Predef.scenario
import com.spn.requests.GetPageIdRequest
import io.gatling.core.Predef._
import scala.concurrent.duration._

object GetPageIdScenario {
  val PageIdInput = csv("data/platform.csv").random


  val PageId = scenario("Get Page Id")
    .feed(PageIdInput)

    .exec(GetPageIdRequest.PageId)
//    .pause(500 milliseconds)
}
