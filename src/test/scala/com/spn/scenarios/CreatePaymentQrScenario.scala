package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.CreatePaymentQrRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

//CreatePaymentQr Scenario
object CreatePaymentQrScenario {

  val dataFeeder=csv("data/platform.csv").circular
  val dataFeederOtpRequirements = csv("data/createPaymentQr.csv").circular

  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )
  val createPaymentQrScenario =scenario("Create Payment QR Scenario")
    .feed(dataFeeder)
.feed(dataFeederOtpRequirements)
.feed(dateTimeFeeder)
    .exec(CreatePaymentQrRequest.createPaymentQrRequest)
}
