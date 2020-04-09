package com.spn.scenarios.journey

import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.{GetProduct, LoginWithEmailRequest}
import com.spn.scenarios.groups.{LoginWithEmailGroup, PaymentGroup, PlayerGroup}
import io.gatling.core.Predef._

object PaymentScenario {

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


  val doPaymentOperationsScenario = scenario("Payment functionality Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)
    .feed(CommonFeedFiles.channelPartnerIdAndAppClientId)
    .feed(CommonFeedFiles.userAuthForScenarioTestingUsersUsingRandom)
    .feed(CommonFeedFiles.dateTimeFeeder)
    .feed(LoginWithEmailGroup.dateOfBirthFeeder)
    .feed(LoginWithEmailGroup.feederDeviceDetails)
    .feed(LoginWithEmailGroup.genderFeeder)
    .feed(LoginWithEmailGroup.pinCodeFeeder)
    .feed(PaymentGroup.feederApplyCoupon)
    .feed(PaymentGroup.feederPaymentModes)
    .feed(PaymentGroup.feederSyncstate)

    .group("Payment Functionality for Logged-In user - Channel - ${channel}") {
      exec(ApiSecurity.getToken)
        .doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
          exec(LoginWithEmailRequest.LoginWithEmail)
            .exec(GetProduct.GetProduct)
              .exec(PaymentGroup.doPaymentOperationsForLoggedInUser)
        }
    }
}
