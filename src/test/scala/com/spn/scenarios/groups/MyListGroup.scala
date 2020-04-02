package com.spn.scenarios.groups

import com.jayway.jsonpath._
import com.spn.common.Constants
import com.spn.requests.{DeleteListRequest, GetListRequest}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object MyListGroup {

  // ALL the functions goes here - starts
  def extractContentIdToBeRemovedFromMyList(session: Session): Session = {
    val respGetMyList = session(Constants.RESP_GET_MY_LIST).as[String]
    println(s"\nextractContentIdToBeRemovedFromMyList : respGetMyList : $respGetMyList")

    val exp = "$.mylist.contents[*].id"
    println(s"\nextractContentIdToBeRemovedFromMyList : Expression : $exp")

    val context = JsonPath.parse(respGetMyList)
    val contentId = context.read[JSONArray](exp)
    println(s"\nextractContentIdToBeRemovedFromMyList : Content Id : $contentId")

    var finalContentId = ""
    if (contentId != null && contentId.size() == 1) {
      finalContentId = contentId.get(0).toString
    } else if (contentId != null && contentId.size() > 1) {
      val size = contentId.size()
      finalContentId = contentId.get(Random.nextInt(size - 1)).toString
    }

    println(s"\nextractContentIdToBeRemovedFromMyList : Final Content Id : $finalContentId")
    if (finalContentId != null && !finalContentId.isEmpty) {
      session.set("assetID", finalContentId)
        .set("deleteList", true)
    } else {
      session
    }
  }

  val deleteList = exec(session => {
    extractContentIdToBeRemovedFromMyList(session)
  })
  .doIf(session => session.contains("deleteList")
    && session("deleteList").as[Boolean].equals(true)) {
    exec(DeleteListRequest.deleteList)
  }

  val myListDistribution = randomSwitch(
    2d -> deleteList
  )
  val doMyListOperations = doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)
    && session.contains(Constants.RESP_SECURITY_TOKEN)) {
    group("Logged In User - My List Operations - Channel - ${channel}") {
      exec(GetListRequest.getUserListRequest)
        .doIf(session => session.contains(Constants.RESP_GET_MY_LIST)) {
          exec(myListDistribution)
        }
    }
  }
}

