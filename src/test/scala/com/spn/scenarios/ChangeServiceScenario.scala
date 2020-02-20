package com.spn.scenarios

import com.spn.requests.ChangeServiceRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object ChangeServiceScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular
  val dataFeederOtpRequirements = csv("data/LoginID.csv").circular
  val dataFeederServiceDetails = csv("data/service_details.csv").circular
  val userCredentials = csv("data/evergent/evergent_data_2.csv.gz").unzip.shard

  val changeServiceScenario = scenario("Create OTP Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederOtpRequirements)
    .feed(userCredentials)
    .feed(dataFeederServiceDetails)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .exec(ChangeServiceRequest.changeServiceRequest)

}
