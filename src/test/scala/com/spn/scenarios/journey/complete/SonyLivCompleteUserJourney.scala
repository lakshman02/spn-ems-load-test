package com.spn.scenarios.journey.complete

import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.scenarios.groups.{PageDetailScreen, _}
import com.spn.scenarios.journey.PlayerScenario.AddXDR_PlaybackFeeder
import io.gatling.core.Predef._

import scala.util.Random

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

  private def randomDoLogin: Boolean = {
//    Random.nextBoolean()
    math.random < 0.50 // with a 0.25 probability
  }

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
        .exec(session => session.set(Constants.REQ_USER_TYPE, Constants.USER_TYPE_GUEST)) //Defaulting to guest for user session
        .group("Guest User App Launch - Channel - ${channel}") {
          exec(UserAppLaunchScenario.userAppLaunchScenario)
        }
        .exec(session => {
          // This is where we are randomly deciding whether to login or not
          var loggedIn = Constants.USER_TYPE_GUEST
          if(randomDoLogin) {
            loggedIn = Constants.USER_TYPE_LOGGED_IN
          }
          println("loggedIn ? "+loggedIn);
          session.set(Constants.REQ_USER_TYPE, loggedIn)
        })
        .doIf(session => session(Constants.REQ_USER_TYPE).as[String].equals(Constants.USER_TYPE_LOGGED_IN)) { // Doing random Login
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
        // This is where home navigation is happening - starts
        .doIfOrElse(session => session(Constants.REQ_USER_TYPE).as[String].equals(Constants.USER_TYPE_LOGGED_IN)) {
          group("Logged in User Home Screen - Channel - ${channel}") {
            exec(HomeScreen.loggedInUserHomeScreenScenario)
          }
            .feed(AddXDR_PlaybackFeeder)
            .exec(PlayerGroup.doPlayerOperations)
            .exec(MyListGroup.doMyListOperations)
        } {
          group("Guest User Home Screen - Channel - ${channel}") {
            exec(HomeScreen.guestUserHomeScreenScenario)
          }
        }
        // This is where home navigation is happening - ends
        // Search functionality starts here
        .doIfOrElse(session => session(Constants.REQ_USER_TYPE).as[String].equals(Constants.USER_TYPE_LOGGED_IN)) {
            randomSwitch(
              20d -> group("Logged in User Performing search - Channel - ${channel}") {
                feed(CommonFeedFiles.contentFeeder)
                  .exec(SearchFunctionalityForUserGroup.doSearchForLoggedInUser)
              }
            )
        } {
          randomSwitch(
            80d -> group("Guest User Performing search - Channel - ${channel}") {
              feed(CommonFeedFiles.contentFeeder)
                .exec(SearchFunctionalityForUserGroup.doSearchForNonLoggedInUser)
            }
          )
        }
        .doIf(doNavigateToDetailsPage) { //TODO - this is in progress - need to conclude
          group("Guest User Page Details - Channel - ${channel}") {
            exec(PageDetailScreen.guestUserDetailScreenScenario)
          }
        }
    }
}
