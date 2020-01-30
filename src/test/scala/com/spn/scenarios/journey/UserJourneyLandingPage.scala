package com.spn.scenarios.journey

import com.spn.requests.{GetInitialConfigRequest,GetMenuRequest,GetPageIdRequest}
import io.gatling.core.Predef._
import scala.concurrent.duration._
object UserJourneyLandingPage {

  val InputFeederFile = csv("data/platform.csv")

  val LandingPage = scenario("User Journey - Landing Page")

    .feed(InputFeederFile)

    .exec(GetInitialConfigRequest.getInitialConfig)
    .pause(500 milliseconds)

    .exec(GetMenuRequest.getMenu)
    .pause(500 milliseconds)

    .exec(GetPageIdRequest.PageId)
    .pause(500 milliseconds)

}
