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

  // Service related details
  val dataFeederServiceDetails = csv("data/service_details.csv").circular
  val dataFeederServiceIDOnlyDetails = csv("data/service_id_data.csv").circular

  // User Specific
  val userAuth1KUsers = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.random

  val userAuth1KUsersUsingCircular = csv("data/evergent/usersWithAuthtoken.csv.gz").unzip.shard.circular

  val userAuth50KUsersUsingCircular = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular


  //Scenario specific - ADD List, GET List, DELETE List
  val userAuth50KUsersUsingCircular_ADDList = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_GetList = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_DeleteList = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular

  //Scenario specific - ADD Settings, GET Settings, Delete Setings
  val userAuth50KUsersUsingCircular_ADDSetting = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_GetSetting = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_DeleteSetting = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular


  //Scenario specific - EPG/REMINDER ADD, GET, DELETE
  val userAuth50KUsersUsingCircular_ADDEpgReminder = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_GetEpgReminder = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_DeleteEpgReminder = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular

  //Scenario specific - REMINDER ADD, GET, DELETE
  val userAuth50KUsersUsingCircular_ADDReminder = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_GetReminder = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_DeleteReminder = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular

  //Scenario specific - ADD XDR, GET XDR, DELETE XDR
  val userAuth50KUsersUsingCircular_ADDXDR = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_GetXDR = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_DeleteXDR = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular

  //Scenario specific - Register Device
  val userAuth50KUsersUsingCircular_RegisterDevice = csv("data/evergent/usersWithAuthtoken50k.csv.gz").unzip.shard.batch.circular

  // inputStagingWeb
  val inputStagingDataFeeder=csv("data/inputStagingWeb.csv").circular

  //singleServiceDetails
  val dataFeederSingleServiceDetails = csv("data/single_serviceId.csv").circular

  //singleChannelpartnerid
  val dataFeederSingleChannelpartnerid = csv("data/single_channel_partner_id.csv").circular

  //assetID
  val dataFeederAssetID= csv("data/assets.csv").circular

  //serialNo
  val dataFeederSerialNum= csv("data/serialNum.csv").circular


  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  // Tray search feeder
  val contentFeeder = csv("data/traySearchQueries.csv").circular
}
