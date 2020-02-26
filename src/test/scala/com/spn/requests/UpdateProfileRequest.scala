package com.spn.requests

import java.util.concurrent.ThreadLocalRandom

import akka.actor.FSM.->
import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

import scala.util.Random

object UpdateProfileRequest {

  val sentHeaders = Map(
    "Authorization" -> "${RESP_AUTH_TOKEN}",
    "x-via-device" -> "true")

  val updateProfile= exec(http("Update Profile Request")
    .post(Config.app_url + Config.UPDATE_PROFILE_URL)
    .headers(sentHeaders)
    .body(StringBody ("""{
    "dateOfBirth": ${dateOfBirth},
    "gender": "${gender}",
    "pincode": "${pincode}",
    "channelPartnerID": "${channelPartnerID}",
    "timestamp": "${getDateTime}"
        }""")).asJson
    .check(status is 200)
    .check(jsonPath("$.resultCode").is("OK")))
}
