package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.common.CommonFeedFiles
import com.spn.requests.ProcessRazorPayOrderRequest
import io.gatling.core.Predef.scenario
import io.gatling.core.Predef._

object ProcessRazorPayOrderScenario {

  val dataFeederPaymentId=csv("data/paymentId.csv").circular

  val processRazorPayOrderScenario =scenario("Process RazorPayOrder Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .feed(CommonFeedFiles.userAuth1KUsers)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(dataFeederPaymentId)
    .exec(ProcessRazorPayOrderRequest.processRazorPayOrderRequest)
}
