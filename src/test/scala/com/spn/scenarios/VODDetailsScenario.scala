package com.spn.scenarios

import com.spn.requests.VODDetailsRequest
import io.gatling.core.Predef.scenario

object VODDetailsScenario {

  val vodDetailsScenario = scenario("VOD Details Mobile Scenario")
    .exec(VODDetailsRequest.vodDetails)

}
