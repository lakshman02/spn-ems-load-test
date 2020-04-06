package com.spn.scenarios.groups

import com.spn.common.Constants
import com.spn.requests._
import com.spn.scenarios.groups.PlansAndSubscriptionGroup
import com.spn.scenarios.groups.PlansAndSubscriptionGroup.extractCouponCodeToBePassed
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
  val invokeCouponOperations = randomSwitch(
    50d -> PlansAndSubscriptionGroup.PlansAndSubscriptionDistribution,
    20d -> openApplyCoupon
  )
  val invokePostSyncstateApi = randomSwitch(
    25d -> exec(PostSyncStateRequest.postSyncStateRequest)
  )

  // Payment Journey goes here - starts
  val doPaymentOperationsForLoggedInUser = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)
    && session.contains(Constants.RESP_AUTH_TOKEN)) {
    group("Payment Functionality for Logged-In user- Channel - ${channel}") {
      exec(GetSyncStateRequest.getSyncState)
        .exec(PaymentModesRequest.Payment_mode)
        .exec(invokeCouponOperations)
        .exec(invokePostSyncstateApi)
    }
  }
}