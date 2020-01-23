package com.spn.scenarios

import com.spn.requests.GetMenuRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

//"Get Menu" scenario
object GetMenuScenario{
  val dataFeeder=csv("platform.csv").random
  val getMenuScenario =scenario("Get Menu Scenario")
    .feed(dataFeeder)
    .exec(GetMenuRequest.getMenu)
}
