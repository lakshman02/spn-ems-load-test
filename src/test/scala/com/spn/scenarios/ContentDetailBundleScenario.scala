package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.ContentDetailBundle
import io.gatling.core.Predef.{scenario, _}

object ContentDetailBundleScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val loginEmailData = csv("data/LoginID.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val contentDetail_BundleScenario = scenario("Content Detail Bundle Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(userCredentials)
    .feed(loginEmailData)
    .exec(ContentDetailBundle.ContentDetailBundle)
}