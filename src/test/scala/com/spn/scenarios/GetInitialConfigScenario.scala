package com.spn.scenarios
import com.spn.common.Constants
import com.spn.requests.GetInitialConfigRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object GetInitialConfigScenario {

  val dataFeeder=csv("data/platform.csv").random

  val getInitialConfigScenario = scenario("Get Initial Config Scenario")
    .feed(dataFeeder)
    .exec(GetInitialConfigRequest.getInitialConfig)
//    .exec(session => {
     // val channel = session("channel").as[String]
     // val response = session(Constants.RESP_INITIAL_CONFIG).as[String]
     // println(s"\nResponse from Session for channel : $channel ; body is : $response")
//      session
//    })
}