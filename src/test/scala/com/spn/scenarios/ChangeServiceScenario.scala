package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.ChangeServiceRequest
import io.gatling.core.Predef.{scenario, _}

object ChangeServiceScenario {

  val dataFeederServiceDetails = csv("data/service_details.csv").circular

  val changeServiceScenario = scenario("Change Service Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CreateOTPScenario.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .feed(CommonFeedFiles.dataFeederServiceDetails)
    .exec(ChangeServiceRequest.changeServiceRequest)

}
