package com.spn.scenarios

import com.spn.requests.ContentDetailBundle
import io.gatling.core.Predef.{scenario, _}

object ContentDetailBundleScenario {
  val dataFeeder = csv("data/platform.csv").circular
  val contentDetail_BundleScenario = scenario("Content Detail Bundle Scenario")
    .feed(dataFeeder)
    .exec(ContentDetailBundle.ContentDetailBundle)
}