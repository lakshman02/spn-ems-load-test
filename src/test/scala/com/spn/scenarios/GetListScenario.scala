package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.GetListRequest
import io.gatling.core.Predef.{scenario, _}

object GetListScenario {

  val getListScenario = scenario("Get List Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_GetList)
    .exec(GetListRequest.getUserListRequest)

}
