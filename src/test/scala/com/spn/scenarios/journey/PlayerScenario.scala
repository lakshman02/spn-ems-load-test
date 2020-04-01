package com.spn.scenarios.journey

import java.util.concurrent.ThreadLocalRandom

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.scenarios.groups.{LoginWithEmailGroup, PlayerGroup, SearchFunctionalityForUserGroup}
import io.gatling.core.Predef._

import scala.util.Random

object PlayerScenario {

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

  val playbackPreviewFeeder = Iterator.continually(
    Map("assetDuration" -> ThreadLocalRandom.current().nextInt(1003000, 6003000),
      "position" -> ThreadLocalRandom.current().nextInt(666000),
      "updatedTime" -> ThreadLocalRandom.current().nextDouble(1000822764043L,1550822764043L),
      "isOnAir" -> true,
      "deviceId" -> Random.nextInt(99999)
    )
  )

  val doPlayerOperationsScenario = scenario("Search Logged In User Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.channelPartnerIdAndAppClientId)
    .feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom)
    .feed(LoginWithEmailGroup.feederDeviceDetails)
    .feed(LoginWithEmailGroup.dateOfBirthFeeder)
    .feed(LoginWithEmailGroup.genderFeeder)
    .feed(LoginWithEmailGroup.pinCodeFeeder)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(playbackPreviewFeeder)

    .exec(ApiSecurity.getToken)
    .exec(LoggedInUserAppLaunchScenario.loggedInUserAppLaunchScenario)
    .exec(PlayerGroup.doPlayerOperations)
}
