package com.spn.scenarios

import java.time.LocalDateTime

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.AddXdrRequest
import io.gatling.core.Predef.{scenario, _}

object AddXdrScenario {

//  val contentIdData = csv("data/contentID.csv").circular


  val addXdrScenario = scenario("Add XDR Scenario")
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular_ADDXDR)
    .feed(CommonFeedFiles.inputStagingDataFeeder)
    .feed(CommonFeedFiles.contentIdData)
    .exec(ApiSecurity.getToken)
    .exec(AddXdrRequest.addXdr)
}