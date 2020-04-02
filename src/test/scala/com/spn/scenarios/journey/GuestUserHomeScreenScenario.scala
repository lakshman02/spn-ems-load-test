package com.spn.scenarios.journey

import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.scenarios.groups.{HomeScreen, UserAppLaunchScenario}
import io.gatling.core.Predef._

object GuestUserHomeScreenScenario {

  val channelFeederOverride = Array(
    //    Map("channel" -> "IPHONE"),
    //    Map("channel" -> "IPAD"),
    Map("channel" -> "ANDROID_PHONE"),
    //    Map("channel" -> "ANDROID_TAB"),
    //    Map("channel" -> "APPLE_TV"),
    Map("channel" -> "FIRE_TV"),
    //    Map("channel" -> "SONY_ANDROID_TV"),
    //    Map("channel" -> "XIAOMI_ANDROID_TV"),
    //    Map("channel" -> "JIO_ANDROID_TV"),
    //    Map("channel" -> "SONY_HTML_TV"),
    //    Map("channel" -> "SAMSUNG_HTML_TV"),
    //    Map("channel" -> "JIO_KIOS"),
    Map("channel" -> "WEB")
    //    Map("channel" -> "IOS")
  ).random

  val guestUserHomeScreenScenario = scenario("Guest User Home Screen Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    //    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)

    .group("Home Screen - Guest User - Channel - ${channel}") {
        exec(ApiSecurity.getToken)
     .exec(UserAppLaunchScenario.userAppLaunchScenario)
        .exec(HomeScreen.doNavigateToGuestUserHomePage)
  }
}


