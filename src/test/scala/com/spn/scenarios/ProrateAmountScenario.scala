package com.spn.scenarios

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.ProrateAmountRequest
import io.gatling.core.Predef.{scenario, _}

object ProrateAmountScenario {

  val prorateAmountScenario = scenario("Prorate Amount Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)

    .feed(CommonFeedFiles.dataFeederSingleChannelpartnerid)
    .feed(CommonFeedFiles.dataFeederServiceIDOnlyDetails)
    .exec(ApiSecurity.getToken)
    .exec(ProrateAmountRequest.prorateAmountRequest)

}
