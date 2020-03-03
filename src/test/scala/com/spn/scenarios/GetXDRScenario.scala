package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.GetXDRRequest
import io.gatling.core.Predef.{scenario, _}

object GetXDRScenario {

  val dataFeederContentId = csv("data/contentID.csv").circular
  val dataFeederUserAuth = csv ("data/evergent/usersWithAuthtoken.csv.gz").unzip.random

  val scn_Get_XDR = scenario("Get XDR scenario")

    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(dataFeederContentId)
    .feed(dataFeederUserAuth)
    .exec(GetXDRRequest.getXDR)
}