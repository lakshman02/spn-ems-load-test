package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.PlaceOrderRequest
import io.gatling.core.Predef.{scenario, _}

object PlaceOrderScenario{

  val placeOrderScenario =scenario("Place Order Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .exec(PlaceOrderRequest.placeOrder)
}
