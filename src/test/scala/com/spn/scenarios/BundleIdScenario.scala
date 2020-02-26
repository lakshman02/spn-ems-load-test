package com.spn.scenarios

import com.spn.requests.BundleIdRequest
import io.gatling.core.Predef._

object BundleIdScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederBundleId = csv("data/bundleId.csv").circular

  val scnbundle = scenario("Get Bundle Id Details")

    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederBundleId)
    .exec(BundleIdRequest.BundleId)
}
