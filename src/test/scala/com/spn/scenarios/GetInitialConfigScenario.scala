package com.spn.scenarios
import com.spn.common.Constants
import com.spn.requests.GetInitialConfigRequest
import com.spn.scenarios.CreateOTPScenario.{dataFeederChannel, dataFeederCluster, dataFeederLocale, dataFeederProperty, dataFeederTenant}
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._
import com.spn.common.CommonFeedFiles
object GetInitialConfigScenario {

  val getInitialConfigScenario = scenario("Get Initial Config Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .exec(GetInitialConfigRequest.getInitialConfig)

}