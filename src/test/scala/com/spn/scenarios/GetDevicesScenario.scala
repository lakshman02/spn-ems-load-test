package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.GetDevicesRequest
import io.gatling.core.Predef.scenario

object GetDevicesScenario {

  val getDevicesScenario = scenario("Get Devices Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .exec(GetDevicesRequest.getDevicesRequest)

}
