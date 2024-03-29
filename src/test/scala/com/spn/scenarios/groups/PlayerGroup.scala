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
  val doPlayerOperationsForLoggedInUser = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)
    && session.contains(Constants.RESP_AUTH_TOKEN)) {
    group("${userType} : Player Functionality - Channel - ${channel}") {

        randomSwitch(50d -> SearchFunctionalityForUserGroup.doTraySearchForEpisodes,
          40d -> SearchFunctionalityForUserGroup.doTraySearchForMovie,
          10d -> SearchFunctionalityForUserGroup.doTraySearchForShow
        )
        .doIf(session => session.contains(Constants.RESP_TRAY_SEARCH_RESPONSE)) {
          exec(session => {
            extractContentIdFromTraySearchResponse(session, "contentId", "")
              .set("id", "${contentId}") //Making a copy as some requests uses is as request parameter
              .set("type", "MOVIE")
          })
            .doIf(session => session.contains("found_content_id") && session("found_content_id").as[Boolean]) {
              exec(AddXdrRequest.addXdr)
                .exec(invokeContinueWatchingOperations)
                .exec(GetUserPlayBackPreviewDetailsRequest.PreviewDetails)
                .doIf(session => session("filter_objectSubtype").as[String].equals("EPISODE")) {
                  exec(session => {
                    extractContentIdFromTraySearchResponse(session, "contentId", "EPISODE")
                      .set("id", "${contentId}") //Making a copy as some requests uses is as request parameter
                  })
                    .exec(invokePlayerNavigationApis)
                }
            }
        }
    }
  }

  val doPlayerOperationsForGuestUser = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
    group("${userType} : Player Functionality - Channel - ${channel}") {

        randomSwitch(50d -> SearchFunctionalityForUserGroup.doTraySearchForEpisodes,
          40d -> SearchFunctionalityForUserGroup.doTraySearchForMovie,
          10d -> SearchFunctionalityForUserGroup.doTraySearchForShow
        )
        .doIf(session => session.contains(Constants.RESP_TRAY_SEARCH_RESPONSE)) {
          exec(session => {
            extractContentIdFromTraySearchResponse(session, "contentId", "")
              .set("id", "${contentId}") //Making a copy as some requests uses is as request parameter
              .set("type", "MOVIE")
          })
            .doIf(session => session.contains("found_content_id") && session("found_content_id").as[Boolean]) {
              exec(GetUserPlayBackPreviewDetailsRequest.PreviewDetails)
                .doIf(session => session("filter_objectSubtype").as[String].equals("EPISODE")) {
                  exec(session => {
                    extractContentIdFromTraySearchResponse(session, "contentId", "EPISODE")
                      .set("id", "${contentId}") //Making a copy as some requests uses is as request parameter
                  })
                    .exec(invokePlayerNavigationApis)
                }
            }
        }
    }
  }
}