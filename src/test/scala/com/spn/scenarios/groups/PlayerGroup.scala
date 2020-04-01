package com.spn.scenarios.groups

import com.spn.common.Constants
import com.spn.requests._
import com.spn.scenarios.groups.SearchFunctionalityForUserGroup.extractContentIdFromTraySearchResponse
import io.gatling.core.Predef._

object PlayerGroup {

  val invokeContinueWatchingOperations = randomSwitch(
    10d -> exec(DeleteXdrRequest.deleteXdrRequest)
  )
  val invokePlayerNavigationApis = randomSwitch(
    50d -> exec(NextRequest.nextRequest),
    25d -> exec(PreviousRequest.Previous),
    25d -> exec(NextAndPreviousRequest.nextAndPreviousRequest)
  )

  // Player Journey goes here - starts
  val doPlayerOperations = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)
    && session.contains(Constants.RESP_AUTH_TOKEN)) {
    group("Player Functionality - Channel - ${channel}") {

      randomSwitch(50d -> SearchFunctionalityForUserGroup.doTraySearchVODForEpisodes,
        40d -> SearchFunctionalityForUserGroup.doTraySearchVODForMovie,
        10d -> SearchFunctionalityForUserGroup.doTraySearchVODForShow
      )
      .doIf(session => session.contains(Constants.RESP_TRAY_SEARCH_VOD_RESPONSE)) {
        exec(session => {
          extractContentIdFromTraySearchResponse(session, "contentId")
//            .set("contentId", "1000005389") // TODO - hard coding for testing
            .set("id" , "${contentId}") //Making a copy as some requests uses is as request parameter
            .set("type", "MOVIE")
        })
          .exec(AddXdrRequest.addXdr)
          .exec(invokeContinueWatchingOperations)
          .exec(GetUserPlayBackPreviewDetailsRequest.PreviewDetails)
          .doIf(session => (session("filter_contentSubtype").as[String].equals("SHOW")
            || session("filter_contentSubtype").as[String].equals("EPISODE"))) {
            exec(invokePlayerNavigationApis)
          }
      }
    }
  }
}