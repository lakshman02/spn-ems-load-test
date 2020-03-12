package com.spn.scenarios
import com.spn.common.CommonFeedFiles
import com.spn.requests.LoginWithEmailRequest
import io.gatling.core.Predef.{scenario, _}

object LoginWithEmailScenario {

  val LoginWithEmailScenario = scenario("Login With Email Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.dataFeederOtpRequirements)
    .exec(LoginWithEmailRequest.LoginWithEmail)
}

