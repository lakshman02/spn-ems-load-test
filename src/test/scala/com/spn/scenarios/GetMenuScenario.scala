package test.scala.com.spn.scenarios

import test.scala.com.spn.requests.GetMenuRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object GetMenuScenario{
  val dataFeeder=csv("platform.csv").random
  val getMenuScenario =scenario("Get Menu Scenario")
    .feed(dataFeeder)
    .exec(GetMenuRequest.getMenu)
}
