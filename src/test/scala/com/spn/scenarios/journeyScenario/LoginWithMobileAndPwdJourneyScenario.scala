package com.spn.scenarios.journeyScenario

import com.spn.requests.GetInitialConfigRequest
import com.spn.requests.GetMenuRequest
import com.spn.requests.GetPageIdRequest
import com.spn.requests.LoginRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object LoginWithMobileAndPwdJourneyScenario  {
  val dataFeeder=csv("data/platform.csv").random
  val loginData = csv("data/LoginID.csv").circular

  val loginWithMobileAndPwdJourney = scenario("LoginWithMobileAndPwdJourneyScenario")
    .feed(dataFeeder)
    .feed(loginData)
    .exec(
      GetInitialConfigRequest.getInitialConfig,
      GetMenuRequest.getMenu,
      GetPageIdRequest.PageId,
      LoginRequest.LoginRequest
    )
}