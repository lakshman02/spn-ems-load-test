package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._
import com.spn.requests.{GetMenuRequest, GetPageIdRequest}

import scala.concurrent.duration._

object GetPageIdScenario {

  val dataFeederPageId = csv("data/pageid.csv").circular

  val PageId = scenario("Get Page Id")

    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(dataFeederPageId)
    .exec(ApiSecurity.getToken)
    .exec(GetPageIdRequest.PageId)

//    .pause(500 milliseconds)
}
