package com.spn.scenarios

import com.spn.requests.GroupOfBundlesRequest
import io.gatling.core.Predef.{scenario, _}

//"Get Menu" scenario
object GroupOfBundlesScenario{
  val dataFeeder=csv("data/platform.csv").random

  val groupOfBundlesScenario =scenario("Group Of Bundles Scenario")
    .feed(dataFeeder)
    .exec(GroupOfBundlesRequest.groupOfBundles)
}
