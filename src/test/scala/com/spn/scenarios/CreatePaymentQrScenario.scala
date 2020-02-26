package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.CreatePaymentQrRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object CreatePaymentQrScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederOtpRequirements = csv("data/payment_details.csv").circular
  val channelPartnerFeeder = csv("data/LoginID.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random


  val createPaymentQrScenario =scenario("Create Payment QR Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
.feed(dataFeederOtpRequirements)
    .feed(userCredentials)
.feed(CreateOTPScenario.dateTimeFeeder)
    .feed(channelPartnerFeeder)
    .exec(CreatePaymentQrRequest.createPaymentQrRequest)
}
