package com.spn.scenarios

import com.spn.requests.GroupOfBundlesRequest
import io.gatling.core.Predef.{scenario, _}

object GroupOfBundlesScenario{

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederBundleId = csv("data/groupBundleId.csv").circular

  val groupOfBundlesScenario =scenario("Group Of Bundles Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederBundleId)
    .exec(GroupOfBundlesRequest.groupOfBundles)
}
