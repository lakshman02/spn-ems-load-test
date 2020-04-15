package com.spn.scenarios.groups

import com.jayway.jsonpath._
import com.spn.common.Constants
import com.spn.requests.{AllSubscriptionsRequest, GetProduct, PostGenericCouponsRequest, ProductsByCouponRequest}
import com.typesafe.config.ConfigException.Null
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object PlansAndSubscriptionGroup {

  // ALL the functions goes here - starts
  def extractCouponCodeToBePassed(session: Session): Session = {
    val respGenericCoupon = session(Constants.RESP_GENERIC_COUPON).as[String]
    println(s"\nextractCouponCodeToBePassed : respGenericCoupon : $respGenericCoupon")

    val CouponCode = "$..couponCodeDetails[*].couponCode"
    println(s"\nextractCouponCodeToBePassed : Expression : $CouponCode")

    val coupon = JsonPath.parse(respGenericCoupon)
    val VoucherCode = coupon.read[JSONArray](CouponCode)
    println(s"\nextractCouponCodeToBePassed : Content Id : $VoucherCode")

    var finalCouponCode = ""
    if (VoucherCode != null && VoucherCode.size() == 1) {
      finalCouponCode = VoucherCode.get(0).toString
    }
    else if (VoucherCode != null && VoucherCode.size() > 1) {
      val size = VoucherCode.size()
      finalCouponCode = VoucherCode.get(Random.nextInt(size - 1)).toString
    }
    println(s"\nextractCouponCodeToBePassed : Final Coupon Code : $finalCouponCode")

    if (finalCouponCode != null && !finalCouponCode.isEmpty) {
      session.set("couponCode", finalCouponCode)
    }
    else {
      println("Coupon Code Could NOT be fetched")
      session
    }
  }

  val openProductsByCoupon = exec(session => {
    extractCouponCodeToBePassed(session)
  }).doIf(session => session.contains("couponCode")) {
    exec(ProductsByCouponRequest.productsByCoupon)
  }

  val PlansAndSubscriptionDistribution = randomSwitch(
    50d -> exec(PostGenericCouponsRequest.Generic_Coupons)
    .randomSwitch(80d -> exec(openProductsByCoupon))
  )
  val doPlansAndSubscriptionOperations = doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)
    && session.contains(Constants.RESP_SECURITY_TOKEN)) {
      exec(GetProduct.GetProduct)
        .exec(AllSubscriptionsRequest.getAllSubscriptions)
        .exec(PlansAndSubscriptionDistribution)
    }
}

