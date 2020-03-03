package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.StoreDropOffReasonRequest
import io.gatling.core.Predef.{scenario, _}

object StoreDropOffReasonScenario{

  val storeDropOffReasonScenario =scenario("Store Drop Off Reason Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .exec(StoreDropOffReasonRequest.storeDropOffReason)
}
