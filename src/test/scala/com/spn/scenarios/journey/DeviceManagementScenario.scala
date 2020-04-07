package com.spn.scenarios.journey

import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.LoginWithEmailRequest
import com.spn.scenarios.groups.{DeviceManagementGroup, LoginWithEmailGroup}
import com.spn.scenarios.journey.LoginScenario.channelFeederOverride
import io.gatling.core.Predef._

object DeviceManagementScenario {

  val usersWithAuth50k = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular

  val channelFeederOverride = Array(
    //    Map("channel" -> "IPHONE"),
    //    Map("channel" -> "IPAD"),
   // Map("channel" -> "ANDROID_PHONE"),
    //    Map("channel" -> "ANDROID_TAB"),
    //    Map("channel" -> "APPLE_TV")
    Map("channel" -> "FIRE_TV"),
    //    Map("channel" -> "SONY_ANDROID_TV"),
    //    Map("channel" -> "XIAOMI_ANDROID_TV"),
    //    Map("channel" -> "JIO_ANDROID_TV"),
    //    Map("channel" -> "SONY_HTML_TV"),
    //    Map("channel" -> "SAMSUNG_HTML_TV"),
    //    Map("channel" -> "JIO_KIOS"),
  //  Map("channel" -> "WEB")
    //    Map("channel" -> "IOS")
  ).circular

  val DeviceManagementScenario = scenario("Device Management Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(usersWithAuth50k)
    .feed(CommonFeedFiles.channelPartnerIdAndAppClientId)
    .feed(LoginWithEmailGroup.feederDeviceDetails)
    .feed(LoginWithEmailGroup.dateOfBirthFeeder)
    .feed(LoginWithEmailGroup.genderFeeder)
    .feed(LoginWithEmailGroup.pinCodeFeeder)

    .group("Device Management Logged-In User - Channel - ${channel}") {
      exec(ApiSecurity.getToken)
        .doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
          exec(LoginWithEmailGroup.doLoginWithEmail)
          .exec(DeviceManagementGroup.doDeviceManagementOperations)
        }
    }
}