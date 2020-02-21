package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.StoreDropOffReasonRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object StoreDropOffReasonScenario{

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val inputStagingDataFeeder=csv("data/inputStagingWeb.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard

  val storeDropOffReasonScenario =scenario("Store Drop Off Reason Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(inputStagingDataFeeder)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .feed(userCredentials)
    .exec(StoreDropOffReasonRequest.storeDropOffReason)
}
