package com.spn.config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Config {
  //Baseurl
  val app_url = "https://apiqa.sonyliv.com/"

  //Api urls
  val URL_INITIAL_CONFIG = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/INITIAL/CONFIG?"
  val GET_MENU_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/MENU/menu"
  val GET_PageID = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/PAGE/${pageID}"
  val Login_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/LOGIN"
  val GET_PROFILE_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/GETPROFILE?channelPartnerID='MSMIND'"
  val GET_ULD_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/ULD"
  val CREATE_OTP_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CREATEOTP"
  val ACCOUNT_SEARCH_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/ACCOUNTS/SEARCH"
  val VOD_DETAILS="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/DETAIL/VOD/${contentId}"
  val GET_ALL_SUBSCRIPTIONS_URL = "${tenant}/1.4/${cluster}/E/${channel}/${propertyName}/SUBSCRIPTION/ALLSUBSCRIPTIONS"
  val GROUP_OF_BUNDLES_URL="AGL/1.4/A/ENG/ANDROID_PHONE/ALL/CONTENT/DETAIL/GROUP_OF_BUNDLES/1700000002"
  val UPDATE_PROFILE_URL="AGL/1.4/A/E/WEB/ALL/USER/UPDATEPROFILE"


  val users = Integer.getInteger("users", 1).toInt
  val rampUp = Integer.getInteger("rampup", 1).toInt
  val duration = Integer.getInteger("duration",1).toInt
  val throughput = Integer.getInteger("throughput", 1).toInt
  val times = Integer.getInteger("times", 1).toInt
  val eachLevelLasting = Integer.getInteger("eachLevelLasting", 1).toInt
  val separatedByRampsLasting = Integer.getInteger("separatedByRampsLasting", 1).toInt
  val startingFrom = Integer.getInteger("startingFrom", 1).toInt
  val defaultResponseTime=Integer.getInteger("responseTime", 1).toInt
  val maxResponseTime=Integer.getInteger("responseTime", 1).toInt

  //http protocol configuration
  val httpProtocol = http
    .baseUrl(app_url)
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
    .contentTypeHeader("application/json")



}

