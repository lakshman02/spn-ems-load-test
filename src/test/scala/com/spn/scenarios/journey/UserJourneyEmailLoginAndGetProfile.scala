package com.spn.scenarios.journey

import java.time.LocalDateTime

import com.spn.common.Constants
import com.spn.requests.{GetProfileRequest, LoginRequest, LoginWithEmailRequest}
import io.gatling.core.Predef._

import scala.concurrent.duration._

object UserJourneyEmailLoginAndGetProfile {

  val dataFeederChannel = csv("data/channel.csv").random
  val dataFeederCluster = csv("data/cluster.csv").random
  val dataFeederLocale = csv("data/locale.csv").random
  val dataFeederProperty = csv("data/property.csv").random
  val dataFeederTenant = csv("data/tenant.csv").random
  val dataFeederLoginData = csv("data/LoginID.csv").random
  val Userlogin = csv("data/evergent/evergent_data_for_email_login.csv.gz").shard.random

  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  val scnLoginAndGetProfile = scenario("User Journey with Email Login and Get Profile")
    .feed(dataFeederTenant)
    .feed(dataFeederCluster)
    .feed(dataFeederLocale)
    .feed(dataFeederChannel)
    .feed(dataFeederProperty)
    .feed(dataFeederLoginData)
    .feed(Userlogin)
    .feed(dateTimeFeeder)

    .exec(LoginWithEmailRequest.LoginWithEmail)
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
