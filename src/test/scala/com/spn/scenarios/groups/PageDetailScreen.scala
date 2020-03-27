package com.spn.scenarios.groups

import com.jayway.jsonpath._
import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.{VODDetailsRequest}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object PageDetailScreen {

  def setTheUrlIdToSession(session: Session, contentType: String, contentSubtype: String): Session = {

    val pageResponse = session(Constants.RESP_PAGE_RESPONSE).as[String]
    println(s"\npageResponse : $pageResponse")


    val expression = "$.containers[*].assets.containers[?(@.metadata.contentType == '"+contentType+"')].id"
    println(s"\nExpression : $expression")

    val context = JsonPath.parse(pageResponse)
    val contentIdFound = context.read[JSONArray](expression)



//            var subContentExpression = "$.containers[*].assets.containers[?(@.metadata.contentType == 'VOD' && @.metadata.contentSubtype == 'SHOW')].id"
//            println(s"\nExpression : $subContentExpression")
//
//            var subContentIdFound = context.read[JSONArray](subContentExpression)

    // Cherry picking a url to navigate to
    var finalIdToNavigateTo = ""
    if (contentIdFound != null && contentIdFound.size() == 1) {
      finalIdToNavigateTo = contentIdFound.get(0).toString
    } else if (contentIdFound != null && contentIdFound.size() > 1) {
      val size = contentIdFound.size()
      finalIdToNavigateTo = contentIdFound.get(Random.nextInt(size - 1)).toString
    }

    println(s"\nFinal id to Navigate To for '$contentType' is : $finalIdToNavigateTo")

    if (finalIdToNavigateTo != null && !finalIdToNavigateTo.isEmpty) {
      session.set(Constants.RESP_CONTENT_ID, finalIdToNavigateTo)
    } else {
      println(s"\nAll attempts failed, for '$contentType' & '$contentSubtype'")
      session
    }
  }


  val openVODDetails = exec(session => {
    setTheUrlIdToSession(session, "VOD","")
  }).doIf(session => session.contains(Constants.RESP_CONTENT_ID )){
    exec(VODDetailsRequest.vodDetails)
  }
  // Channels
  val openVODPage = randomSwitch(
    100d -> openVODDetails)
  // ALL the functions goes here - ends

  // App launch User Journey goes here - starts
  val guestUserDetailScreenScenario =doIf(session => session.contains(Constants.REQ_USER_TYPE) ) {
          exec(openVODPage)
        }

  // App launch User Journey goes here - ends
}
