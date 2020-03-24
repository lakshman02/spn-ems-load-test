package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.AddListRequest
import io.gatling.core.Predef.{scenario, _}

object AddListScenario {


  val addListScenario =scenario("Add List Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_ADDList)
    .feed(CommonFeedFiles.dataFeederAssetID)
    .exec(ApiSecurity.getToken)
    .exec(AddListRequest.addList)
}
