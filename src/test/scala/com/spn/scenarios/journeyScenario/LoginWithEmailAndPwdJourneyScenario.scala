package com.spn.scenarios.journeyScenario

import com.spn.requests.GetInitialConfigRequest
import com.spn.requests.GetMenuRequest
import com.spn.requests.GetPageIdRequest
import com.spn.requests.LoginWithEmailRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object LoginWithEmailAndPwdJourneyScenario {

  val dataFeeder=csv("data/platform.csv").circular
  val loginData = csv("data/LoginID.csv").circular

  val loginWithEmailAndPWDJourneyScenario = scenario("Create Login with Email and PWD Journey Scenario")
    .feed(dataFeeder)
    .feed(loginData)
    .exec(
      GetInitialConfigRequest.getInitialConfig,
      GetMenuRequest.getMenu,
      GetPageIdRequest.PageId,
      LoginWithEmailRequest.LoginWithEmail)

}
