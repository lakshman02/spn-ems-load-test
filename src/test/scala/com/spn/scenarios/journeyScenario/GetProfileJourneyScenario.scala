package com.spn.scenarios.journeyScenario

import com.spn.requests.GetInitialConfigRequest
import com.spn.requests.GetMenuRequest
import com.spn.requests.GetPageIdRequest
import com.spn.requests.LoginRequest
import com.spn.requests.GetProfileRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario


object GetProfileJourneyScenario {
  val dataFeeder=csv("data/platform.csv").random
  val loginData = csv("data/LoginID.csv").circular

  val getProfileJourneyScenario = scenario("Get Profile Journey Scenario")
    .feed(dataFeeder)
    .exec(
    GetInitialConfigRequest.getInitialConfig,
    GetMenuRequest.getMenu,
    GetPageIdRequest.PageId,
    LoginRequest.LoginRequest,
    GetProfileRequest.getProfile)
}
