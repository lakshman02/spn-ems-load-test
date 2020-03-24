package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.RemoveDevicesRequest
import io.gatling.core.Predef.scenario

object RemoveDevicesScenario {

  val removeDevicesScenario = scenario("Remove Devices Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.dataFeederSerialNum)
    .exec(ApiSecurity.getToken)
    .exec(RemoveDevicesRequest.removeDevicesRequest)

}
