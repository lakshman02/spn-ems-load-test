package com.spn.scenarios

import com.spn.requests.GetULDRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object GetULDScenario {

  val dataFeeder = csv("platform.csv").random

  val getULDScenario = scenario("Get ULD Scenario")
    .feed(dataFeeder)
    .exec(GetULDRequest.getULD)

   // .exec { session => println(session) session }
}
