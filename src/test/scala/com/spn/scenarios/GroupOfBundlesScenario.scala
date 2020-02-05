package com.spn.scenarios

import com.spn.requests.GroupOfBundlesRequest
import io.gatling.core.Predef.{scenario, _}

//"Get Menu" scenario
object GroupOfBundlesScenario{

  val groupOfBundlesScenario =scenario("Group Of Bundles Scenario")
    .exec(GroupOfBundlesRequest.groupOfBundles)
}
