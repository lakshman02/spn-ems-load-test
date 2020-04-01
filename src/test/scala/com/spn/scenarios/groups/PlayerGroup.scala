package com.spn.scenarios.groups

import com.spn.common.Constants
import com.spn.requests._
import com.spn.scenarios.groups.PageDetailScreen.setTheUrlIdToSession
import io.gatling.core.Predef._

object PlayerGroup {

  val invokeContinueWatchingOperations = randomSwitch(
    10d -> exec(DeleteXdrRequest.deleteXdrRequest)
  )
  val invokePlayerNavigationApis = randomSwitch(
    50d -> exec(NextRequest.nextRequest),
    20d -> exec(PreviousRequest.Previous),
    20d -> exec(NextAndPreviousRequest.nextAndPreviousRequest)
  )
  // Player Journey goes here - starts
  val doPlayerOperations = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
    doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)) {
      exec(session => {
        setTheUrlIdToSession(session, "VOD","","","contentId")
      })
      .exec(AddXdrRequest.addXdr)
        .exec(invokeContinueWatchingOperations)
        .exec(GetUserPlayBackPreviewDetailsRequest.PreviewDetails)
        .exec(invokePlayerNavigationApis)
    }
  }
}
