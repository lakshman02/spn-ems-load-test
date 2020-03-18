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

    .group("App Launch - Guest User"){
      exec(GetTokenRequest.getToken)
        .doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)){
          exec(session => {
            val getSecurityToken = session(Constants.RESP_SECURITY_TOKEN).as[String]
            println(s"\nRESP_SECURITY_TOKEN is: $getSecurityToken")

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
            .exec(GetPageIdRequest.PageId) // Definitly invoke Home Page
            .pause(1, 3 seconds)
            .doIf("GO_TO_MOVIE" == true) { //Decide if we need to take the user to Movies Page?
              exec(GetPageIdRequest.PageId)
            }.doIf("GO_TO_TV_SHOWS" == true) { //Decide if we need to take the user to TV Shows Page?
              exec(GetPageIdRequest.PageId)
            }
            .pause(1, 3 seconds)
            .exec(GetULDRequest.getULD)
        }
    }
}





