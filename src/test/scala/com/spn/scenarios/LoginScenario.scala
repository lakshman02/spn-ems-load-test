package com.spn.scenarios
import com.spn.common.CommonFeedFiles
import com.spn.requests.LoginRequest
import io.gatling.core.Predef.{scenario, _}

  object LoginScenario {

    val loginScenario = scenario("Login With Mobile Scenario")
      .feed(CommonFeedFiles.dataFeederTenant)
      .feed(CommonFeedFiles.dataFeederCluster)
      .feed(CommonFeedFiles.dataFeederLocale)
      .feed(CommonFeedFiles.dataFeederChannel)
      .feed(CommonFeedFiles.dataFeederProperty)
      .feed(CommonFeedFiles.dateTimeFeeder)
      .feed(CommonFeedFiles.userAuth1KUsers)
      .feed(CommonFeedFiles.dataFeederOtpRequirements)
      .exec(LoginRequest.LoginRequest)


  }



