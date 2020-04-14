package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.netty.handler.codec.http.HttpResponseStatus

object PostApplyCouponRequest {

  val ApplyCoupon = http("Apply Coupon")
    .post(Config.app_url + Config.Post_Apply_Coupon_URL)
    .headers(Config.sentHeaders)
    .body(StringBody(
      """ {
                       "couponCode": "${couponCode}",
                        "price": "${price}",
                        "productID": "${productID}",
                        "channelPartnerID": "${channelPartnerID}",
                        "timestamp": "${getDateTime}"
                       }""")).asJson
    //  We don't have a co-relation between Generic Coupon and Products where we can apply the coupon.
    //  So in this case specifically, since a customer can fetch the coupon code from a Generic Coupon page and try to apply,
    //  we are checking if the system is throwing back a 400 Bad request. This is also a valid case.
    .check(status in (200, 400))
    .check(checkIf((response: Response, _: Session) => isResponseOK(response)){
      jsonPath("$.resultCode").is("OK")
    })

  def isResponseOK(response: Response): Boolean = response.status.eq(HttpResponseStatus.OK)

}
