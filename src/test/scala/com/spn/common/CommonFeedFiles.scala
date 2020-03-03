package com.spn.common

import java.time.LocalDateTime

import io.gatling.core.Predef._

object CommonFeedFiles {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular


  // TODO - check the impact of this if removed
  val dataFeederOtpRequirements = csv("data/LoginID.csv").circular

  // User Specific
  val userAuth1KUsers = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random


  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )
}
