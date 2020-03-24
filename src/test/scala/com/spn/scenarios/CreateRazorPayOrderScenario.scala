package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.CreateRazorPayOrderRequest
import io.gatling.core.Predef.{scenario, _}

object CreateRazorPayOrderScenario{


  val createRazorPayOrderScenario =scenario("Create Razor Pay Order Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .exec(ApiSecurity.getToken)
    .exec(CreateRazorPayOrderRequest.createRazorPayOrder)
}
