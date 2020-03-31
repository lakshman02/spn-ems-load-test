package com.spn.scenarios.journey

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.requests.TraySearchRequest
import io.gatling.core.Predef._

object GuestUserSearchScenario {

  val channelFeederOverride = Array(
    //    Map("channel" -> "IPHONE"),
    //    Map("channel" -> "IPAD"),
    Map("channel" -> "ANDROID_PHONE"),
    //    Map("channel" -> "ANDROID_TAB"),
    //    Map("channel" -> "APPLE_TV")
    Map("channel" -> "FIRE_TV"),
    //    Map("channel" -> "SONY_ANDROID_TV"),
    //    Map("channel" -> "XIAOMI_ANDROID_TV"),
    //    Map("channel" -> "JIO_ANDROID_TV"),
    //    Map("channel" -> "SONY_HTML_TV"),
    //    Map("channel" -> "SAMSUNG_HTML_TV"),
    //    Map("channel" -> "JIO_KIOS"),
    Map("channel" -> "WEB")
    //    Map("channel" -> "IOS")
  ).circular

  val guestSearch = scenario("Guest User Search")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom)
    .feed(CommonFeedFiles.contentFeeder)
    .group("Guest User Search - Channel - ${channel}") {
      exec(ApiSecurity.getToken)
        .exec(TraySearchRequest.traySearchRequest)
    }
}
