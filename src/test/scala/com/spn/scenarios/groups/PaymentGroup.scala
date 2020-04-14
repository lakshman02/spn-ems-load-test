package com.spn.scenarios.groups

import com.jayway.jsonpath.JsonPath
import com.spn.common.Constants
import com.spn.requests._
import com.spn.scenarios.groups.PlansAndSubscriptionGroup.{extractCouponCodeToBePassed, openProductsByCoupon}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object PaymentGroup {
  def extractProductIdToBePassed(session: Session): Session = {
    val respGetProduct = session(Constants.RESP_GET_PRODUCT).as[String]
    println(s"\nextractProductIdToBePassed : respGetProduct : $respGetProduct")

    val ProductId = "$..productsResponseMessage[*].planInfo[*].skuORQuickCode"
    println(s"\nextractProductIDToBePassed : Expression : $ProductId")

    val getProduct = JsonPath.parse(respGetProduct)
    val productID = getProduct.read[JSONArray](ProductId)
    println(s"\nextractProductIdToBePassed : product Id : $productID")

    var finalProductID = ""
    if (productID != null && productID.size() == 1) {
      finalProductID = productID.get(0).toString
    }
    else if (productID != null && productID.size() > 1) {
      val size = productID.size()
      finalProductID = productID.get(Random.nextInt(size - 1)).toString
    }
    println(s"\nextractProductIDToBePassed : Final product Code : $finalProductID")

    if (finalProductID != null && !finalProductID.isEmpty) {
      session.set("productID", finalProductID)
    }
    else {
      println("productID Could NOT be fetched")
      session
    }
  }

  private def randomPrice: Float = {
    val r = Random
    r.nextInt(1000)
  }

  // TODO - Check if we can avoid hard coding here?
  val feederPaymentModes = Array(
    Map("serviceID" -> "1mn_99_india", "serviceType" -> "PRODUCT", "platform" -> "Desktop", "appType" -> "Web", "deviceType" -> "webClient", "languageCode" -> "en_US")
  ).circular
  //  , "productID" -> "daily_india"
  val feederApplyCoupon = Array(
    Map("price" -> randomPrice)
  ).circular
  // TODO - Check if we can avoid hard coding here?
  val feederSyncState = Array(
    Map("packageId" -> "daily_india", "packageName" -> "Daily", "state" -> "User Logged In", "isTransactionCompleted" -> false, "isPaymentSuccessful" -> false)
  ).circular
  val openApplyCoupon = exec(session => {
    extractProductIdToBePassed(session)
  }).exec(session => {
    extractCouponCodeToBePassed(session)
  }).doIf(session => session.contains("couponCode") && session.contains("productID")) {
    exec(PostApplyCouponRequest.ApplyCoupon)
  }
  val invokeApplyCouponApi = randomSwitch(
    10d -> openApplyCoupon
  )
  val invokePaymentMode = randomSwitch(
    10d -> PaymentModesRequest.Payment_mode
  )
  val invokeSyncStateApi = randomSwitch(
    70d -> exec(GetSyncStateRequest.getSyncState),
    30d -> exec(PostSyncStateRequest.postSyncStateRequest)
  )
  // Payment Journey goes here - starts
  val doPaymentOperationsForLoggedInUser = doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)) {
    group("Payment Functionality for Logged-In user- Channel - ${channel}") {
      exec(PostGenericCouponsRequest.Generic_Coupons)
        .exec(invokeApplyCouponApi)
        .exec(invokePaymentMode)
        .exec(openProductsByCoupon)
        .exec(invokeSyncStateApi)
    }
  }
}