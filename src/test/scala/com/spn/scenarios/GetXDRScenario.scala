package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.GetXDRRequest
import io.gatling.core.Predef.{scenario, _}

object GetXDRScenario {

  val dataFeederContentId = csv("data/contentID.csv").circular

  val scn_Get_XDR = scenario("Get XDR scenario")

    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(dataFeederContentId)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_GetXDR)
    .exec(ApiSecurity.getToken)
    .exec(GetXDRRequest.getXDR)
}
