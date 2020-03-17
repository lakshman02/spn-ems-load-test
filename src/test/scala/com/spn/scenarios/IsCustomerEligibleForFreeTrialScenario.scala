package com.spn.scenarios

import com.spn.common.CommonFeedFiles
import com.spn.requests.IsCustomerEligibleForFreeTrialRequest
import io.gatling.core.Predef.{scenario, _}

object IsCustomerEligibleForFreeTrialScenario {


  val checkCustomerEligibleForFreeTrial = scenario("Check if the user is eligible for free trial Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .exec(IsCustomerEligibleForFreeTrialRequest.checkCustomerEligibleForFreeTrial)

}
