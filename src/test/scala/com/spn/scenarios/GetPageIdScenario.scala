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
    .exec(session => session.set("paginationFrom","0").set("paginationTo","5").set("pageSuffix", "Pagination:0-5"))
    .exec(GetPageIdRequest.PageId)
    .pause(1, 3 seconds)
    .exec(session => session.set("paginationFrom","5").set("paginationTo","10").set("pageSuffix", "Pagination:5-10"))
    .exec(GetPageIdRequest.PageId)
}
