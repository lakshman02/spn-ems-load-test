package com.spn.scenarios

import com.spn.requests.PaymentModesRequest
import io.gatling.core.Predef._
object PaymentModesScenario {

 val bodydatafeeder = csv("data/LoginID.csv")
  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
 val usersWithAuthtokenDataFeeder = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard
  val scnPaymentMode = scenario("Post Payment Mode")
    .feed(CreateOTPScenario.dateTimeFeeder)
    .feed(bodydatafeeder)
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(usersWithAuthtokenDataFeeder)
    .exec(PaymentModesRequest.Payment_mode)
}
