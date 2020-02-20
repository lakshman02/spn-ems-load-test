package com.spn.config
import java.time.LocalDateTime

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Config {
  //Baseurl

 // var app_url: String = System.getProperty("appURL")

//  if(app_url == null || app_url.equals("")) {
//    app_url = "https://apiqa.sonyliv.com/"
//  }

  //  val app_url = "https://apiqa.sonyliv.com/"
  val app_url = "https://apipreprod.sonyliv.com/"

  //Api urls
  val URL_INITIAL_CONFIG = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/INITIAL/CONFIG?"
  val GET_MENU_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/MENU/menu"
  val GET_PageID = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/PAGE/${pageID}"
//  val GET_PageID = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}${RANDOM_PAGE_URL}"
  val Login_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/LOGIN"
  val GET_PROFILE_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/GETPROFILE?channelPartnerID=MSMIND1"
  val GET_ULD_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/ULD"
  val CREATE_OTP_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CREATEOTP"
  val ACCOUNT_SEARCH_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/ACCOUNTS/SEARCH"
  val Post_Generic_Coupon = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/GENERICCOUPONS "
  val Post_Subscription_History = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/HISTORY"
  val VOD_DETAILS="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/DETAIL/VOD/1000001081"
  val GET_ALL_SUBSCRIPTIONS_URL = "${tenant}/1.4/${cluster}/E/${channel}/${propertyName}/SUBSCRIPTION/ALLSUBSCRIPTIONS"
  val GROUP_OF_BUNDLES_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/DETAIL/GROUP_OF_BUNDLES/1700000002"
  val UPDATE_PROFILE_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/UPDATEPROFILE"
//  val UPDATE_PROFILE_URL="AGL/1.4/A/ENG/WEB/ALL/USER/UPDATEPROFILE"
  val IS_SUBSCRIBED_URL="${tenant}/1.4/${cluster}/E/${channel}/${propertyName}/SUBSCRIPTION/ISSUBSCRIBED"
  val GET_PRODUCTS_URL="${tenant}/1.4/${cluster}/E/${channel}/${propertyName}/SUBSCRIPTION/GETPRODUCTS"
  val ACTIVE_SUBSCRIPTIONS_URL = "${tenant}/1.4/${cluster}/E/WEB/${propertyName}/SUBSCRIPTION/ACTIVESUBSCRIPTIONS"
  val CONTENT_DETAIL_BUNDLE_URL = "${tenant}/1.4/${cluster}/${locale}/ANDROID_PHONE/ALL/CONTENT/DETAIL/BUNDLE/${contentId}"
  val CREATE_PAYMENT_QR = "AGL/1.4/A/E/WEB/IN/SUBSCRIPTION/CREATEPAYMENTQR"
  val CHECK_FREE_TRIAL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/ISCUSTOMERELIGIBLEFORFREETRIAL"
  val SUBSCRIPTION_PAYMENTURL = "AGL/1.4/A/E/WEB/IN/SUBSCRIPTION/PAYMENTURL"
  val PLACE_ORDER_URL="${tenant}/1.4/${cluster}/E/${channel}/${propertyName}/SUBSCRIPTION/PLACEORDER"




  val PRODUCTS_BY_COUPON_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/PRODUCTSBYCOUPON"
  val STORE_DROP_OFF_REASON_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/STOREDROPOFFREASON"
  val Post_Apply_Coupon_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/APPLYCOUPON"
  val UPGRADABLE_PLANS_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/UPGRADABLEPLANS"
  val PRORATE_AMOUNT_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/PRORATEAMOUNT"
  val PAYMENT_MODES_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/PAYMENTMODES"
  val SUBSCRIPTION_REMOVE_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/REMOVE"
  val GetSyncState_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/SUBSCRIPTION/SYNCSTATE?packageId=daily_india"

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
    .header("restful","yes")

}

