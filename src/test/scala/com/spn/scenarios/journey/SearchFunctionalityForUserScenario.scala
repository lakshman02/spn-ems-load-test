package com.spn.scenarios.journey

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.scenarios.groups.{LoginWithEmailGroup, SearchFunctionalityForUserGroup}
import io.gatling.core.Predef._

object SearchFunctionalityForUserScenario {

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

  val doSearchScenario = scenario("Search Logged In User Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.channelPartnerIdAndAppClientId)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom)
    .feed(CommonFeedFiles.contentFeeder)
    .feed(LoginWithEmailGroup.feederDeviceDetails)
    .feed(LoginWithEmailGroup.dateOfBirthFeeder)
    .feed(LoginWithEmailGroup.genderFeeder)
    .feed(LoginWithEmailGroup.pinCodeFeeder)

    .group("Search Functionality - Channel - ${channel}") {
      exec(ApiSecurity.getToken)
      .randomSwitch(
        20d -> group("Search Functionality for Logged-In user - Channel - ${channel}"){
          exec(LoginWithEmailGroup.doLoginWithEmail).exec(SearchFunctionalityForUserGroup.doSearchForLoggedInUser)
        },
        80d -> group("Search Functionality for Guest user - Channel - ${channel}"){
          exec(SearchFunctionalityForUserGroup.doSearchForNonLoggedInUser)
        }
      )
    }
}