package com.spn.scenarios.groups

import java.util.concurrent.ThreadLocalRandom

import com.spn.common.Constants
import com.spn.requests._
import io.gatling.core.Predef._

import scala.util.Random

object SearchLoggedInUserGroup {
  // All possible feeders - encapsulated inside this group - starts
  private def randomSerialNumber: String = {
    val r = Random
    "d6acc46e-5a09-d432-1afb-" + r.nextInt(2000000000)
  }

  private def randomModelNumber: String = {
    val r = Random
    "abc-" + r.nextInt(1000)
  }

  val feederDeviceDetails = Array(
    Map("serialNo" -> randomSerialNumber, "deviceType" -> "webClient")
  ).circular

  val dateOfBirthFeeder = Iterator.continually(
    Map("dateOfBirth" -> ThreadLocalRandom.current().nextInt(1551081657, 1582617662))
  )

  // All possible feeders - encapsulated inside this group - ends

  val invokeSearchApis = randomSwitch(
    10d -> exec(DeleteSearchHistoryRequest.DeleteSearchHistory),
    20d -> exec(AddSearchedItemRequest.AddSearchedItem),
    40d -> exec(GetSearchHistoryRequest.getSearchHistory),
    1d -> exec(DeleteAllSearchHistory.DeleteAllSearchHistory)
  )

  // Logged in User search Journey goes here - starts
  val doLoginWithEmailAndSearch = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
    exec(LoginWithEmailRequest.LoginWithEmail)
      .doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)) {
        exec(invokeSearchApis)
      }
  }
}