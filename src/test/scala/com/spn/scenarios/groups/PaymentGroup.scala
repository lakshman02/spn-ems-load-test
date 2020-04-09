package com.spn.scenarios.groups

import com.spn.common.Constants
import com.spn.requests._
import com.spn.scenarios.groups.PlansAndSubscriptionGroup
import com.spn.scenarios.groups.PlansAndSubscriptionGroup.{extractCouponCodeToBePassed, openProductsByCoupon}
import io.gatling.core.Predef._

import scala.util.Random

object PaymentGroup {

  private def randomPrice: Float = {
    val r = Random
    r.nextInt(100)
  }

  val feederPaymentModes = Array(
    Map("serviceID" -> "1mn_99_india", "serviceType" -> "PRODUCT", "platform" -> "Desktop", "appType" -> "Web", "deviceType" -> "webClient", "languageCode" -> "en_US")
  ).circular

  val feederApplyCoupon = Array(
    Map("price" -> randomPrice, "productID" -> "daily_india")
  ).circular

  val feederSyncstate = Array(
    Map("packageId" -> "daily_india", "packageName" -> "Daily", "state" -> "User Logged In", "isTransactionCompleted" -> false, "isPaymentSuccessful" -> false)
  ).circular

  val openApplyCoupon = exec(session => {
    extractCouponCodeToBePassed(session)
  }).doIf(session => session.contains("couponCode")) {
    exec(PostApplyCouponRequest.ApplyCoupon)
  }
  //Reusing productsByCoupon and generic coupons
  val invokeApplyCouponApi = randomSwitch(
    10d -> openApplyCoupon
  )
  val invokePostSyncstateApi = randomSwitch(
    50d -> exec(GetSyncStateRequest.getSyncState),
    10d -> exec(PostSyncStateRequest.postSyncStateRequest)
  )
  // Payment Journey goes here - starts
  val doPaymentOperationsForLoggedInUser = doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)) {
    group("Payment Functionality for Logged-In user- Channel - ${channel}") {
      exec(PaymentModesRequest.Payment_mode)
        .exec(PostGenericCouponsRequest.Generic_Coupons)
        .exec(openProductsByCoupon)
        .exec(invokeApplyCouponApi)
        .exec(invokePostSyncstateApi)
    }
  }
}