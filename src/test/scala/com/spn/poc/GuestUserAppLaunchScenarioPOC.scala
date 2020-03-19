package com.spn.poc

import com.spn.common.{CommonFeedFiles, Constants}
import com.spn.requests.{GetInitialConfigRequest, GetPageIdRequest, GetTokenRequest, GetULDRequest}
import io.gatling.core.Predef._

import scala.concurrent.duration._
object GuestUserAppLaunchScenarioPOC {

  val repeatCounter = 0
  val possibility_one = 50d
  val possibility_two = 50d

  val guestUserAppLaunchScenario = scenario("Guest User App Launch Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)


    .group("App Launch - Guest User") {

      exec(GetInitialConfigRequest.getInitialConfig)
      //        .repeat(repeatCounter){
      //          randomSwitch(
      //              possibility_one -> exec(GetTokenRequest.getToken),
      //            possibility_two -> exec(GetULDRequest.getULD))}
      //        }
      .randomSwitch(
        possibility_one -> exec(GetTokenRequest.getToken),
        possibility_two -> exec(GetULDRequest.getULD))
    }

}
