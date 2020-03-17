package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.ChangeServiceRequest
import io.gatling.core.Predef.{scenario, _}

object ChangeServiceScenario {

  val changeServiceScenario = scenario("Change Service Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)

    .feed(CommonFeedFiles.dataFeederSingleChannelpartnerid)
    .feed(CommonFeedFiles.dataFeederServiceIDOnlyDetails)

    .exec(ChangeServiceRequest.changeServiceRequest)

}
