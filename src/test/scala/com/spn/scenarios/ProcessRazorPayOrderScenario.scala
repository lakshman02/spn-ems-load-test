package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.requests.ProcessRazorPayOrderRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object ProcessRazorPayOrderScenario {


  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val inputStagingDataFeeder=csv("data/inputStagingWeb.csv").circular
  val dataFeederPaymentId=csv("data/paymentId.csv").circular
  val userCredentials = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val processRazorPayOrderScenario =scenario("Process RazorPayOrder Scenario")
    .feed(dataFeederChannel)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederProperty)
    .feed(dataFeederTenant)
    .feed(inputStagingDataFeeder)
    .feed(userCredentials)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .feed(dataFeederPaymentId)
    .exec(ProcessRazorPayOrderRequest.processRazorPayOrderRequest)
}
