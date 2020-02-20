package com.spn.scenarios

import com.spn.requests.GetProfileRequest
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object GetProfileScenario {
  val dataFeederChannel = csv("data/channel.csv").random
  val dataFeederCluster = csv("data/cluster.csv").random
  val dataFeederLocale = csv("data/locale.csv").random
  val dataFeederProperty = csv("data/property.csv").random
  val dataFeederTenant = csv("data/tenant.csv").random
  val usersWithAuthtokenDataFeeder = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.circular

  val getProfileScenario =scenario("Get Profile Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(usersWithAuthtokenDataFeeder)
    .exec(GetProfileRequest.getProfile)
}
