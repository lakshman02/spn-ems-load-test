package com.spn.scenarios

import com.spn.requests.PostSyncStateRequest
import com.spn.scenarios.ProrateAmountScenario.{authFeeder, dataFeederChannel, dataFeederCluster, dataFeederLocale, dataFeederProperty, dataFeederTenant, loginEmailData}
import io.gatling.core.Predef._

object PostSyncStateScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val loginEmailData = csv("data/inputStagingWeb.csv").circular
  val dataFeederService = csv("data/service_details.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard


  val postSyncStateScenario = scenario("Post Sync State Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .feed(loginEmailData)
    .feed(authFeeder)
    .feed(dataFeederService)
    .feed(userCredentials)

    .exec(PostSyncStateRequest.postSyncStateRequest)
}
