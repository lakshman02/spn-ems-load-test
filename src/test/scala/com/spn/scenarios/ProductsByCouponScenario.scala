package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.ProductsByCouponRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object ProductsByCouponScenario{

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val inputStagingDataFeeder=csv("data/inputStagingWeb.csv").circular
  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val productsByCouponScenario =scenario("Products By Coupon Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(inputStagingDataFeeder)
    .feed(dateTimeFeeder)
    .exec(ProductsByCouponRequest.productsByCoupon)
}
