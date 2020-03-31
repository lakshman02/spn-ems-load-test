package com.spn.scenarios.journey

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.scenarios.groups.SearchLoggedInUserGroup
import io.gatling.core.Predef._

object SearchLoggedInUserScenario {

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

  val SearchLoggedInUserScenario = scenario("Search Logged In User Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.channelPartnerIdAndAppClientId)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom)
    .feed(SearchLoggedInUserGroup.feederDeviceDetails)
    .feed(SearchLoggedInUserGroup.dateOfBirthFeeder)
    .feed(CommonFeedFiles.contentFeeder)

    .group("Search Logged In User - Channel - ${channel}") {
      exec(ApiSecurity.getToken)
        .exec(SearchLoggedInUserGroup.doLoginWithEmailAndSearch)
    }
}