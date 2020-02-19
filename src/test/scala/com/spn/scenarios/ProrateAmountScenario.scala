package com.spn.scenarios

import com.spn.requests.{ ProrateAmountRequest}
import io.gatling.core.Predef.{scenario, _}

object ProrateAmountScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val loginEmailData = csv("data/inputStagingWeb.csv").circular
  val authFeeder = csv("data/LoginID.csv").circular


  val prorateAmountScenario = scenario("Login With Email Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .feed(loginEmailData)
    .feed(authFeeder)

    .exec(ProrateAmountRequest.prorateAmountRequest)

}
