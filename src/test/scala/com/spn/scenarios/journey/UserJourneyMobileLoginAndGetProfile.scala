package com.spn.scenarios.journey

import java.time.LocalDateTime

import com.spn.common.Constants
import com.spn.requests.{ActiveSubscription, AllSubscriptionsRequest, GetProfileRequest, LoginRequest}
import io.gatling.core.Predef._

import scala.concurrent.duration._

object UserJourneyMobileLoginAndGetProfile {

  val dataFeederChannel = csv("data/channel.csv").random
  val dataFeederCluster = csv("data/cluster.csv").random
  val dataFeederLocale = csv("data/locale.csv").random
  val dataFeederProperty = csv("data/property.csv").random
  val dataFeederTenant = csv("data/tenant.csv").random
  val dataFeederLoginData = csv("data/LoginID.csv").random
  val Userlogin = csv("data/evergent/evergent_data_2.csv").circular

  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val scnLoginAndGetProfile = scenario("User Journey with Mobile Login and Get Profile")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederLoginData)
    .feed(Userlogin)
    .feed(dateTimeFeeder)

    .exec(LoginRequest.LoginRequest)
    .doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)){
      exec(session => {
        val authToken = session(Constants.RESP_AUTH_TOKEN).as[String]
        println(s"\nRESP_AUTH_TOKEN is: $authToken")
        session
      })
      .pause(1, 3 seconds)
      .exec(GetProfileRequest.getProfile)
    }
}
