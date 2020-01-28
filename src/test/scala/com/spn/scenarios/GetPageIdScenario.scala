package com.spn.scenarios

import io.gatling.core.Predef.scenario
import com.spn.requests.{GetPageIdRequest,GetMenuRequest,GetInitialConfigRequest}
import io.gatling.core.Predef._
import scala.concurrent.duration._

object GetPageIdScenario {
  val PageIdInput = csv("platform.csv").queue


  val scnLandingPage = scenario("User Journey 1 - landing page") // changed from PageId scenario name
    .feed(PageIdInput)

    .exec(GetInitialConfigRequest.getInitialConfig)
    .pause(500 milliseconds)

    .exec(GetMenuRequest.getMenu)
    .pause(500 milliseconds)

    .exec(GetPageIdRequest.PageId)
    .pause(500 milliseconds)
}
