package com.spn.scenarios.journey.complete

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.scenarios.groups.{PageDetailScreen, _}
import io.gatling.core.Predef._

object SonyLivCompleteUserJourney {

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
    Map("channel" -> "WEB"),
//    Map("channel" -> "IOS")
  ).random

  val doNavigateToDetailsPage = false
  val doUserLogin = true


  val doSonyLivCompleteUserJourney = scenario("Complete User Journey")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    //    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)

    .group("Complete User Journey - Channel - ${channel}") {
     // exec(session => session.set(Constants.REQ_USER_TYPE, Constants.USER_TYPE_GUEST))
      exec(ApiSecurity.getToken)
        .group("Guest User App Launch - Channel - ${channel}") {
          exec(UserAppLaunchScenario.userAppLaunchScenario)
        }
        .group("Guest User Home Screen - Channel - ${channel}") {
          exec(HomeScreen.guestUserHomeScreenScenario)
        }
        .doIf(doUserLogin) {
          group("Email Login - Channel - ${channel}") {
            feed(CommonFeedFiles.dateTimeFeeder)
              .feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom)
              .feed(LoginWithEmailGroup.feederDeviceDetails)
              .feed(CommonFeedFiles.channelPartnerIdAndAppClientId)
              .feed(LoginWithEmailGroup.dateOfBirthFeeder)
              .feed(LoginWithEmailGroup.genderFeeder)
              .feed(LoginWithEmailGroup.pinCodeFeeder)
              .exec(LoginWithEmailGroup.doLoginWithEmail)
          }
        }
        .group("Search Functionality - Channel - ${channel}") {
            doIfOrElse(doUserLogin){
              randomSwitch(
                20d -> group("Search Functionality for Logged-In user - Channel - ${channel}"){
                  feed(CommonFeedFiles.contentFeeder)
                  .exec(LoginWithEmailGroup.doLoginWithEmail).exec(SearchFunctionalityForUserGroup.doSearchForLoggedInUser)
                }
              )
            }
          {
            randomSwitch(
              80d -> group("Search Functionality for Guest user - Channel - ${channel}"){
                feed(CommonFeedFiles.contentFeeder)
                .exec(SearchFunctionalityForUserGroup.doSearchForNonLoggedInUser)
              }
            )
          }
        }
        .doIf(doNavigateToDetailsPage) {
          group("Guest User Page Details - Channel - ${channel}") {
            exec(PageDetailScreen.guestUserDetailScreenScenario)
          }
        }
    }
}
