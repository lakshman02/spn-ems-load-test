package com.spn.scenarios.journey.complete

import com.jayway.jsonpath._
import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.{GetInitialConfigRequest, GetPageIdRequest, GetProfileRequest, GetTokenRequest, GetULDRequest}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray
import com.spn.scenarios.groups.{PageDetailScreen,_}

object SonyLivCompleteUserJourney {

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
  ).random

  val guestUserDetailScreenScenario = scenario("Guest User Detail Screen Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    //    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)

    .group("Detail Screen - Guest User - Channel - ${channel}") {
      exec(session => session.set(Constants.REQ_USER_TYPE, Constants.USER_TYPE_GUEST))
        .exec(UserAppLaunchScenario.userAppLaunchScenario)
        .exec(PageDetailScreen.guestUserDetailScreenScenario)

    }







}
