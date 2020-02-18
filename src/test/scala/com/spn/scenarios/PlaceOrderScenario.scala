package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.{PlaceOrderRequest, UpdateProfileRequest}
import io.gatling.core.Predef.{scenario, _}

object PlaceOrderScenario{

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val inputStagingDataFeeder=csv("data/inputStagingWeb.csv").circular

  val placeOrderScenario =scenario("Place Order Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(inputStagingDataFeeder)
    .exec(PlaceOrderRequest.placeOrder)
}
