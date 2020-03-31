package com.spn.scenarios.groups

import java.util.concurrent.ThreadLocalRandom

import com.spn.common.Constants
import com.spn.requests._
import io.gatling.core.Predef._

import scala.util.Random

object SearchFunctionalityForUserGroup {

  val invokeSearchApis = randomSwitch(
    2d -> exec(DeleteSearchHistoryRequest.DeleteSearchHistory),
    2d -> exec(DeleteAllSearchHistory.DeleteAllSearchHistory)
  )

  // Logged in User search Journey goes here - starts
  val doSearchForLoggedInUser = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
    doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)) {
      exec(GetSearchHistoryRequest.getSearchHistory)
        .exec(AddSearchedItemRequest.AddSearchedItem)
        .exec(invokeSearchApis)
    }
  }
  val doSearchForNonLoggedInUser = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
    exec(TraySearchRequest.traySearchRequest)
  }
}
