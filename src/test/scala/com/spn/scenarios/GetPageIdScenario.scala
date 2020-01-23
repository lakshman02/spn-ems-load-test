package com.spn.scenarios

import io.gatling.core.Predef.scenario
import com.spn.requests.GetPageIdRequest
import io.gatling.core.Predef._

object GetPageIdScenario {
  val PageIdInput = csv("platform.csv")

  val PageID = scenario("Get PageID")
    .feed(PageIdInput)
    .exec(GetPageIdRequest.PageId)
}
