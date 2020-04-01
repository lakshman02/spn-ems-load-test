package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.DeleteXdrRequest
import io.gatling.core.Predef.{scenario, _}

object DeleteXdrScenario {

//  val dataFeederContentId = csv("data/contentID.csv").circular

  val deleteXdrScenario = scenario("Delete XDR Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_DeleteXDR)
    .feed(CommonFeedFiles.contentIdData)
    .exec(ApiSecurity.getToken)
    .exec(DeleteXdrRequest.deleteXdrRequest)

}
