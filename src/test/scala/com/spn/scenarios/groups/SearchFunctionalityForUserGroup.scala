package com.spn.scenarios.groups

import java.util.concurrent.ThreadLocalRandom

import com.jayway.jsonpath.JsonPath
import com.spn.common.Constants
import com.spn.requests._
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object SearchFunctionalityForUserGroup {

  val invokeSearchApis = randomSwitch(
    2d -> exec(DeleteSearchHistoryRequest.DeleteSearchHistory),
    2d -> exec(DeleteAllSearchHistory.DeleteAllSearchHistory)
  )

  def extractContentIdFromTraySearchResponse(session: Session, contentIdKey: String, contentType : String): Session = {

    val traySearchResponse = session(Constants.RESP_TRAY_SEARCH_RESPONSE).as[String]
    println(s"\nextractContentIdFromTraySearchResponse : traySearchResponse : $traySearchResponse")

    var expression = "$..metadata.contentId"

    if(contentType.equals("EPISODE")) {
      expression = "$.containers[*].containers[?(@.metadata.contentSubtype == 'EPISODE' && @.metadata.episodeNumber > 2 && @.metadata.episodeNumber < 14)].id"
    } else {
      expression = "$.containers[*].containers[*].id"
    }

    println(s"\nextractContentIdFromTraySearchResponse : expression : $expression")

    val context = JsonPath.parse(traySearchResponse)
    val contentIdFound = context.read[JSONArray](expression)

    var contentId = ""
    if (contentIdFound != null && contentIdFound.size() == 1) {
      contentId = contentIdFound.get(0).toString
    } else if (contentIdFound != null && contentIdFound.size() > 1) {
      val size = contentIdFound.size()
      contentId = contentIdFound.get(Random.nextInt(size - 1)).toString
    }

    println(s"\nextractContentIdFromTraySearchResponse : Content Id for Further Requests is : $contentId")

    if (contentId != null && !contentId.isEmpty) {
      session.set(contentIdKey, contentId)
    } else {
      println(s"\nextractContentIdFromTraySearchResponse : All attempts failed!!")
      session
    }
  }

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

  val doTraySearchForEpisodes = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
    exec(session => {
      session
        .set("query", "The Good Doctor")//TODO possible randomization?
        .set("filter_objectSubtype", "EPISODE")
        .set("orderBy", "lastBroadcastDate")
        .set("sortOrder", "desc")
    })
      .exec(TraySearchRequest.traySearchRequest)
  }

  val doTraySearchForMovie = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
    exec(session => {
      session
        .set("query", "")
        .set("filter_objectSubtype", "MOVIE")
        .set("orderBy", "lastBroadcastDate")
        .set("sortOrder", "desc")
    })
      .exec(TraySearchRequest.traySearchRequest)
  }

  val doTraySearchForShow = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
    exec(session => {
      session
        .set("query", "")
        .set("filter_objectSubtype", "SHOW")
        .set("orderBy", "lastBroadcastDate")
        .set("sortOrder", "desc")
    })
      .exec(TraySearchRequest.traySearchRequest)
  }
}
