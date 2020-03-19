package com.spn.config
import java.time.LocalDateTime

import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Config {
  //Baseurl

//  var app_url: String = System.getProperty("appURL")
//
//  if(app_url == null || app_url.equals("")) {
//    app_url = "https://apiqa.sonyliv.com/"
//  }

  //  val app_url = "https://apiqa.sonyliv.com/"
 val app_url = "https://apipreprod.sonyliv.com/"

  //Api urls
  val URL_INITIAL_CONFIG = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/INITIAL/CONFIG"
  val GET_MENU_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/MENU/menu"
//  val GET_PageID = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/PAGE/${pageID}"
  val GET_PageID = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/${RANDOM_PAGE_URL}"
  val Login_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/LOGIN"
  val GET_PROFILE_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/GETPROFILE?channelPartnerID=${channelPartnerID}"
  val GET_ULD_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/ULD"
  val CREATE_OTP_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CREATEOTP"
  val ACCOUNT_SEARCH_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/ACCOUNTS/SEARCH"
  val Post_Generic_Coupon = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/GENERICCOUPONS "
  val Post_Subscription_History = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/HISTORY"
  val VOD_DETAILS="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/DETAIL/VOD/${contentId}"
  val GET_ALL_SUBSCRIPTIONS_URL = "${tenant}/1.4/${cluster}/E/${channel}/${propertyName}/SUBSCRIPTION/ALLSUBSCRIPTIONS"
  val GROUP_OF_BUNDLES_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/DETAIL/GROUP_OF_BUNDLES/${groupBundleId}"
  val UPDATE_PROFILE_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/UPDATEPROFILE"
  val IS_SUBSCRIBED_URL="${tenant}/1.4/${cluster}/E/${channel}/${propertyName}/SUBSCRIPTION/ISSUBSCRIBED"
  val GET_PRODUCTS_URL="${tenant}/1.4/${cluster}/E/${channel}/${propertyName}/SUBSCRIPTION/GETPRODUCTS"
  val ACTIVE_SUBSCRIPTIONS_URL = "${tenant}/1.4/${cluster}/E/WEB/${propertyName}/SUBSCRIPTION/ACTIVESUBSCRIPTIONS"
  val CONTENT_DETAIL_BUNDLE_URL = "${tenant}/1.4/${cluster}/${channel}/${propertyName}/CONTENT/DETAIL/BUNDLE/${bundleId}"
  val CREATE_PAYMENT_QR = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/CREATEPAYMENTQR"
  val CHECK_FREE_TRIAL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/ISCUSTOMERELIGIBLEFORFREETRIAL"
  val SUBSCRIPTION_PAYMENTURL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/PAYMENTURL"
  val PLACE_ORDER_URL="${tenant}/1.4/${cluster}/E/${channel}/${propertyName}/SUBSCRIPTION/PLACEORDER"
  val PROCESSRAZORPAYORDER_URL = "${tenant}/1.4/${cluster}/ENG/${channel}/${propertyName}/SUBSCRIPTION/PROCESSRAZORPAYORDER"
  val PRODUCTS_BY_COUPON_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/PRODUCTSBYCOUPON"
  val STORE_DROP_OFF_REASON_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/STOREDROPOFFREASON"
  val Post_Apply_Coupon_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/APPLYCOUPON"
  val UPGRADABLE_PLANS_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/UPGRADABLEPLANS"
  val PRORATE_AMOUNT_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/PRORATEAMOUNT"
  val PAYMENT_MODES_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/PAYMENTMODES"
  val SUBSCRIPTION_REMOVE_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/REMOVE"
  val GetSyncState_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/SUBSCRIPTION/SYNCSTATE?packageId=${packageId}"
  val CHANGE_SERVICE_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/CHANGESERVICE"
  val Post_Sync_State_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/SUBSCRIPTION/SYNCSTATE"
  val LA_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/GETLAURL"
  val VIDEO_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/VIDEOURL/VOD/${contentId}"
  val SHOW_DETAIL_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/DETAIL/SHOW/${Group_Of_Bundle}"
  val GET_SEARCH_HISTORY_URL ="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/GETSEARCHHISTORY"
  val GET_USER_PLAYBACK_PREVIEW_DETAILS_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/PREVIEW/GETUSERPLAYBACKPREVIEWDETAILS"
  val DELETE_LIST_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/DELETEMYLIST"
  val ADDSEARCHEDITEM = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/ADDSEARCHEDITEM"
  val EPISODE_DETAILS = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/DETAIL/EPISODE/${episodeid}"
  val BUNDLE = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/DETAIL/BUNDLE/${bundleId}"
  val DELETE_SEARCH_HISTORY = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/DELETESEARCHHISTORY"
  val TRAY_SEARCH_VOD_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/TRAY/SEARCH/VOD"
  val GET_LIST ="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/MYLIST"
  val MOVIE_DETAIL_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/DETAIL/MOVIE/${movieId}"
  val ADD_LIST_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/ADDMYLIST"
  val GET_TOKEN_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/GETTOKEN"
  val GENERATE_DEVICE_ACTIVATION_CODE_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/GENERATEDEVICEACTIVATIONCODE"
  val TRAY_SEARCH_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/TRAY/SEARCH"
  val SEARCH_DESCRIPTION_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/TRAY/SUGGESTION"
  val CREATE_RAZOR_PAY_ORDER_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/SUBSCRIPTION/CREATERAZORPAYORDER"
  val ADD_XDR_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/XDR"
  val NEXT_URL ="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/NEXT/${contentId}"
  val DELETE_XDR_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/XDR/DELETE"
  val PREVIOUS = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/PREVIOUS/${contentId}"
  val GET_XDR = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/XDR"
  val NEXT_AND_PREVIOUS_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/NEXTANDPREVIOUS/${contentId}"
  val GET_DRM_DEVICEID_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/CONTENT/GETDRMDEVICEID"
  val GET_SETTINGS_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/SETTINGS/GETSETTINGS"
  val ADD_REMINDER_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/FIXTURE/ADDREMINDER"
  val GET_REMINDER_URL= "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/FIXTURE/GETREMINDERS/ALL"
  val DELETE_REMINDER_URL="${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/FIXTURE/DELETEREMINDER/${contentId}"
  val DELETE_SETTINGS_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/SETTINGS/DELETESETTINGS"
  val USER_PERFERENCES_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/PREFERENCES"
  val GET_DEVICES_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/DEVICES/USER/GETDEVICES"
  val ADD_SETTINGS_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/SETTINGS/ADDSETTINGS"
  val EPG_REMINDER_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/EPG/REMINDER"
  val TRAY_RECOMMENDATION_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/TRAY/RECOMMENDATION"
  val USER_RECOMMENDATION_DETAIL_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/RECOMMENDATION/${contentId}/DETAIL/EPISODE"
  val EPG_REMINDER_DELETE_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/EPG/REMINDER/${assetId}/${startDateTime}"

  val EPG_REMINDER_GET_LIST_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/EPG/LIST"
  val USER_RECOMMENDATION_LANDING_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/USER/RECOMMENDATION/${pageid}/LANDING/SHOW"
  val REMOVE_DEVICES_URL = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/DEVICES/USER/REMOVEDEVICES/${RESP_DEVICE_SERIAL_NUMBER}"

  val DELETE_ALL_SEARCH_HISTORY = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/DELETEALLSEARCHHISTORY"

  val users = Integer.getInteger("users", 1).toInt
  val rampUp = Integer.getInteger("rampup", 1).toInt
  val nothingFor  = Integer.getInteger("nothingFor").toInt
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

  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true")

  val sentHeadersNew = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true",
    "build_number" -> "1.0",
    "app_version" -> "1.0")

}

