package com.spn.scenarios

import com.spn.requests.GetUserPlayBackPreviewDetailsRequest
import io.gatling.core.Predef.{scenario, _}

object GetUserPlayBackPreviewDetailsScenario{

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val previewDetailsDataFeeder=csv("data/previewDetails.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random


  val getUserPlayBackPreviewDetailsScenario =scenario("Get User PlayBack Preview Details Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(previewDetailsDataFeeder)
    .feed(userCredentials)
    .exec(GetUserPlayBackPreviewDetailsRequest.PreviewDetails)
}
