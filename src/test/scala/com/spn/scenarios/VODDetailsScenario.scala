package com.spn.scenarios

import com.spn.requests.VODDetailsRequest
import io.gatling.core.Predef.{scenario, _}

object VODDetailsScenario {

 val dataFeeder = csv("data/platform.csv").circular

  val vodDetailsScenario = scenario("VOD Details Mobile Scenario")
    .feed(dataFeeder)
    .exec(VODDetailsRequest.vodDetails)

}
