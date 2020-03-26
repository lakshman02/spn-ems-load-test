package com.spn.scenarios.journey

import com.spn.common.{CommonFeedFiles, Constants}
import com.spn.scenarios.groups.UserAppLaunchScenario
import io.gatling.core.Predef._
import com.spn.requests.GetProfileRequest

object LoggedInUserAppLaunchScenario  {

  val channelFeederOverride = Array(
    Map("channel" -> "IPHONE"),
    Map("channel" -> "IPAD"),
    Map("channel" -> "ANDROID_PHONE"),
//    Map("channel" -> "ANDROID_TAB"),
    Map("channel" -> "APPLE_TV"),
    Map("channel" -> "FIRE_TV")
//    Map("channel" -> "SONY_ANDROID_TV"),
//    Map("channel" -> "XIAOMI_ANDROID_TV"),
//    Map("channel" -> "JIO_ANDROID_TV"),
//    Map("channel" -> "SONY_HTML_TV"),
//    Map("channel" -> "SAMSUNG_HTML_TV"),
//    Map("channel" -> "JIO_KIOS"),
//    Map("channel" -> "WEB"),
//    Map("channel" -> "IOS")
  ).circular

  val loggedInUserAppLaunchScenario = scenario("Logged In User App Launch Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
//    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuth50KUsersUsingCircular)
    .feed(CommonFeedFiles.inputStagingDataFeeder)

    .group("App Launch - Logged In User - Channel - ${channel}") {
      exec(session => session.set(Constants.REQ_USER_TYPE, Constants.USER_TYPE_LOGGED_IN))
      .exec(UserAppLaunchScenario.userAppLaunchScenario)

    }
}