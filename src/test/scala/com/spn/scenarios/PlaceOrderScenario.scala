package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.{PlaceOrderRequest, UpdateProfileRequest}
import io.gatling.core.Predef.{scenario, _}

object PlaceOrderScenario{

  val dataFeeder=csv("data/platform.csv").random

  val placeOrderScenario =scenario("Place Order Scenario")
    .feed(dataFeeder)
    .exec(PlaceOrderRequest.placeOrder)
}
