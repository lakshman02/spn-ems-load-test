package com.spn.scenarios
import com.spn.common.Constants
import com.spn.requests.GetInitialConfigRequest
import com.spn.scenarios.CreateOTPScenario.{dataFeederChannel, dataFeederCluster, dataFeederLocale, dataFeederProperty, dataFeederTenant}
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object GetInitialConfigScenario {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular


  val getInitialConfigScenario = scenario("Get Initial Config Scenario")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .exec(GetInitialConfigRequest.getInitialConfig)

}