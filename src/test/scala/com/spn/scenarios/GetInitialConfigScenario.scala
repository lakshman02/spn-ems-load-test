package com.spn.scenarios
import com.spn.requests.GetInitialConfigRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object GetInitialConfigScenario {

  val dataFeeder=csv("platform.csv").random

  val getInitialConfigScenario = scenario("Get Initial Config Scenario")
    .feed(dataFeeder)
    .exec(GetInitialConfigRequest.getInitialConfig)
}