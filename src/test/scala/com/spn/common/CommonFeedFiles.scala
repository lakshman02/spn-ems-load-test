package com.spn.common

import java.time.LocalDateTime

import io.gatling.core.Predef._

object CommonFeedFiles {

  val dataFeederChannel = csv("data/channel.csv").circular
  val dataFeederCluster = csv("data/cluster.csv").circular
  val dataFeederLocale = csv("data/locale.csv").circular
  val dataFeederProperty = csv("data/property.csv").circular
  val dataFeederTenant = csv("data/tenant.csv").circular

 //data for xdr apis
 val contentIdData = csv("data/contentID.csv").circular

 //data for next, nextPrevious Apis
 val NextPreviousContentidDataFeeder = csv("data/next_nextAndPrevious_data.csv").circular

 //data for previous api
 val previousContentidDataFeeder = csv("data/previous_data.csv").circular

 // data for playback preview Details
 val previewDetailsDataFeeder=csv("data/previewDetails.csv").circular

 //TODO - this should be from some feed file
  val channelPartnerIdAndAppClientId = Array(
    Map("channelPartnerID" -> "MSMIND1", "appClientId" -> "1212475532.1575468358")
  ).circular

  // TODO - check the impact of this if removed
  val dataFeederOtpRequirements = csv("data/LoginID.csv").circular

  // Service related details
  val dataFeederServiceDetails = csv("data/service_details.csv").circular
  val dataFeederServiceIDOnlyDetails = csv("data/service_id_data.csv").circular

  // User Specific
  val userAuth1KUsers = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular

  val userAuth1KUsersUsingCircular = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular

  val userAuth50KUsersUsingCircular = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular
  //login with email data
  val userEmailLoginData= csv("data/evergent/evergent_data_for_email_login.csv.gz").unzip.shard.batch.circular
  val userAuthForScenarioTestingUsersUsingRandom = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.random

  //Scenario specific - ADD List, GET List, DELETE List
  val userAuth50KUsersUsingCircular_ADDList = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_GetList = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_DeleteList = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular

  //Scenario specific - ADD Settings, GET Settings, Delete Setings
  val userAuth50KUsersUsingCircular_ADDSetting = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_GetSetting = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_DeleteSetting = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular

  val userAuthForScenarioIndividualTestingUsersUsingRandom = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.random


  //Scenario specific - EPG/REMINDER ADD, GET, DELETE
  val userAuth50KUsersUsingCircular_ADDEpgReminder = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_GetEpgReminder = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_DeleteEpgReminder = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular

  //Scenario specific - REMINDER ADD, GET, DELETE
  val userAuth50KUsersUsingCircular_ADDReminder = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_GetReminder = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_DeleteReminder = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular

  //Scenario specific - ADD XDR, GET XDR, DELETE XDR
  val userAuth50KUsersUsingCircular_ADDXDR = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_GetXDR = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular
  val userAuth50KUsersUsingCircular_DeleteXDR = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular

  //Scenario specific - Register Device
  val userAuth50KUsersUsingCircular_RegisterDevice = csv("data/evergent/evergent_file_with_agl_access_token_100k.csv.gz").unzip.shard.batch.circular

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

 //languageCode
 val dataFeederLangCode = csv("data/languageCode.csv").circular



  val dateTimeFeeder = Iterator.continually(
    Map("getDateTime" -> LocalDateTime.now())
  )

  // Tray search feeder
  val contentFeeder = csv("data/traySearchQueries.csv").circular

 // Tray recommendation Recosense feeder
 val trayReconsenseFeeder=csv("data/trayRecommendationRecosenseData.csv").circular

 val trayRailTypeFeeder = csv("data/railType.csv").circular

 val objectSubTypeFeeder = csv("data/objectSubType.csv").circular

 //skuORQuickCode feeder
 val skuORQuickCodeFeeder=csv("data/skuORQuickCodeData.csv").circular

  val feederTraySearchForEpisode = csv("data/tray.search.episode.csv").random
  val feederTraySearchForMovie = csv("data/tray.search.movie.csv").random
  val feederTraySearchForShow = csv("data/tray.search.show.csv").random

}
