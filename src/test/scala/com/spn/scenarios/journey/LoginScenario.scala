package com.spn.scenarios.journey

import java.util.concurrent.ThreadLocalRandom

import com.spn.common.{ApiSecurity, CommonFeedFiles}
import com.spn.scenarios.groups.LoginWithEmailGroup
import io.gatling.core.Predef._

import scala.util.Random

object LoginScenario {

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

  private def randomSerialNumber: String = {
    val r = Random
    "d6acc46e-5a09-d432-1afb-" + r.nextInt(2000000000)
  }

  private def randomModelNumber: String = {
    val r = Random
    "abc-" + r.nextInt(1000)
  }

  val feederDeviceDetails = Array(
    Map("serialNo" -> randomSerialNumber, "deviceName" -> "webClient", "deviceModelNumber" -> randomModelNumber, "deviceType" -> "webClient")
  ).circular

  val dateOfBirthFeeder = Iterator.continually(
    Map("dateOfBirth" -> ThreadLocalRandom.current().nextInt(1551081657, 1582617662))
  )

  val genderFeeder = Array(
    Map("gender" -> "Male"),
    Map("gender" -> "Female")
  ).random

  val pinCodeFeeder = Iterator.continually(
    Map("pincode" -> ThreadLocalRandom.current().nextInt(500072, 600000))
  )

  val loginScenario = scenario("Login Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom)
    .feed(feederDeviceDetails)
    .feed(CommonFeedFiles.channelPartnerIdAndAppClientId)
    .feed(dateOfBirthFeeder)
    .feed(genderFeeder)
    .feed(pinCodeFeeder)

    .group("Email Login- Channel - ${channel}") {
      exec(ApiSecurity.getToken)
        .exec(LoginWithEmailGroup.doLoginWithEmail)
    }
}