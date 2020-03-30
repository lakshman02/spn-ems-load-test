package com.spn.scenarios.journey

import com.jayway.jsonpath._
import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.scenarios.groups.LoginWithEmailGroup
import io.gatling.core.Predef._
import net.minidev.json.JSONArray
import com.spn.requests.{GetProfileRequest, LoginWithEmailRequest}

import scala.util.Random

object LoginScenario  {

  val channelFeederOverride = Array(
//    Map("channel" -> "IPHONE"),
//    Map("channel" -> "IPAD"),
//    Map("channel" -> "ANDROID_PHONE"),
//    Map("channel" -> "ANDROID_TAB"),
    Map("channel" -> "APPLE_TV")
//    Map("channel" -> "FIRE_TV")
//    Map("channel" -> "SONY_ANDROID_TV"),
//    Map("channel" -> "XIAOMI_ANDROID_TV"),
//    Map("channel" -> "JIO_ANDROID_TV"),
//    Map("channel" -> "SONY_HTML_TV"),
//    Map("channel" -> "SAMSUNG_HTML_TV"),
//    Map("channel" -> "JIO_KIOS"),
//    Map("channel" -> "WEB"),
//    Map("channel" -> "IOS")
  ).circular

  val loginScenario = scenario("Login Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
//    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom)
    .feed(CommonFeedFiles.inputStagingDataFeeder)

    .group("Login - Channel - ${channel}") {
      exec(ApiSecurity.getToken)
        .exec(LoginWithEmailGroup.doLoginWithEmail)
    }
}