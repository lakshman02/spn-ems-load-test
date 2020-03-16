package com.spn.scenarios.journey

import com.spn.common.{CommonFeedFiles, Constants}
import com.spn.requests.{ GetInitialConfigRequest, GetPageIdRequest, GetTokenRequest, GetULDRequest}
import io.gatling.core.Predef._

import scala.concurrent.duration._

object GuestUserAppLaunchScenario {

  val guestUserAppLaunchScenario = scenario("Guest User App Launch Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)

    .exec(GetTokenRequest.getToken)
    .doIf(session => session.contains(Constants.RESP_TOKEN)){
      exec(session => {
        val getToken = session(Constants.RESP_TOKEN).as[String]
        println(s"\nRESP_TOKEN is: $getToken")

        session
      })
        .pause(1, 3 seconds)
        .exec(GetInitialConfigRequest.getInitialConfig)
        .exec(session => {
          val initialConfigResponse = session(Constants.RESP_RANDOM_PAGE_URL).as[String]
          println(s"\nRESP_RANDOM_PAGE_URL : $initialConfigResponse")
          session

        })
        .pause(1, 3 seconds)
        .exec(GetULDRequest.getULD)
        .pause(1, 3 seconds)
        .exec(GetPageIdRequest.PageId)
    }
}





