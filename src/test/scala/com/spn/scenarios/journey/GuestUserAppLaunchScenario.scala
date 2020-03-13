package com.spn.scenarios.journey

import java.time.LocalDateTime

import com.spn.common.{CommonFeedFiles, Constants}
import com.spn.requests.{ActiveSubscription, AllSubscriptionsRequest, GetInitialConfigRequest, GetPageIdRequest, GetProfileRequest, GetTokenRequest, GetULDRequest, LoginRequest}
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
        .pause(1, 3 seconds)
        .exec(GetULDRequest.getULD)
        .pause(1, 3 seconds)
        .exec(GetPageIdRequest.PageId)
    }
}

// 1) We will have to identify the targetType type and it should be PAGE
// 2) Multiple result objects as in menu, menu mobile and menu footer



