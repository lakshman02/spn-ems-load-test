package com.spn.scenarios

import java.time.LocalDateTime
import com.spn.requests.CreateRazorPayOrderRequest
import io.gatling.core.Predef.{scenario, _}

object CreateRazorPayOrderScenario{

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val inputDataFeeder=csv("data/inputStagingWeb.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val createRazorPayOrderScenario =scenario("Create Razor Pay Order Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(inputDataFeeder)
    .feed(dateTimeFeeder)
    .feed(userCredentials)
    .exec(CreateRazorPayOrderRequest.createRazorPayOrder)
}
