package com.spn.scenarios

import com.spn.requests.GetDRMDeviceIdRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object GetDRMDeviceIdScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val deviceFeeder = csv("data/device_details.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random


  val getDRMDeviceIdScenario = scenario("Get DRMDevice ID Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(userCredentials)
    .feed(deviceFeeder)
    .exec(GetDRMDeviceIdRequest.getDRMDeviceIdRequest)
}


