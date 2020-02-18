package com.spn.scenarios

import com.spn.requests.{GetProfileRequest, GetTransactionStatusRequest}
import io.gatling.core.Predef._
import io.gatling.core.Predef.scenario

object GetTransactionStatusScenario {
  val dataFeeder=csv("data/platform.csv").random
  val dataFeederForRequestBody= csv("data/createPaymentQr.csv").random

  val getTransactionStatusScenario =scenario("Get Transaction Scenario")
    .feed(dataFeeder)
    .feed(dataFeederForRequestBody)
    .exec(GetTransactionStatusRequest.getTransactionStatus)

}
