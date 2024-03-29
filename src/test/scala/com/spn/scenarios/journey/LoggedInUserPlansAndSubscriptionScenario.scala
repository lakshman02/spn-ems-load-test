package com.spn.scenarios.journey

import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.scenarios.groups.{LoginWithEmailGroup, PlansAndSubscriptionGroup, UserAppLaunchScenario}
import io.gatling.core.Predef._

object LoggedInUserPlansAndSubscriptionScenario {

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

  val scnPlanAndSubscription = scenario("Logged In User Plans and Subscription Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.channelPartnerIdAndAppClientId)
    .feed(CommonFeedFiles.dataFeederLangCode)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom)
    .feed(CommonFeedFiles.contentFeeder)
    .feed(LoginWithEmailGroup.feederDeviceDetails)
    .feed(LoginWithEmailGroup.dateOfBirthFeeder)
    .feed(LoginWithEmailGroup.genderFeeder)
    .feed(LoginWithEmailGroup.pinCodeFeeder)


    .group("Login User App Launch - Logged In User - Channel - ${channel}") {
      exec(ApiSecurity.getToken)
        .exec(LoginWithEmailGroup.doLoginWithEmail)
        .exec(UserAppLaunchScenario.userAppLaunchScenario)
        .exec(session => session.set(Constants.REQ_USER_TYPE, Constants.USER_TYPE_LOGGED_IN)) // We added this extra of setting the session val
        .doIf(session => session(Constants.REQ_USER_TYPE).as[String].equals(Constants.USER_TYPE_LOGGED_IN)) {
          group("${userType} : Plans and Subscription Operations - Channel - ${channel}") {
            exec(PlansAndSubscriptionGroup.doPlansAndSubscriptionOperations)
          }
        }
    }
}

