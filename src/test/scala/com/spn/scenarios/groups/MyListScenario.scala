package com.spn.scenarios.groups

import com.jayway.jsonpath._
import com.spn.common.Constants
import com.spn.requests.{GetListRequest, DeleteListRequest,AddListRequest}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object MyListScenario {

  // ALL the functions goes here - starts
  def setRandomContentIdToSession(session: Session): Session = {
    val exp = "$.resultObj.mylist.contents[*].id "
    println(s"\nExpression : $exp")

    val respGetMyList = session(Constants.RESP_GET_MY_LIST).as[String]
    println(s"\nrespGetMyList : $respGetMyList")

    val context = JsonPath.parse(respGetMyList)
    val contentId = context.read[JSONArray](exp)
    println(s"\nContent Id : $contentId")

    session.set("assetID", contentId)
  }
  val deleteList = exec(session => {
    setRandomContentIdToSession(session)
  })
    .doIf(session => session.contains("assetID"))
    {
      exec(AddListRequest.addList)
      exec(DeleteListRequest.deleteList)
    }

    val myListDistribution = randomSwitch(
      10d -> deleteList
    )
  val myListScenario = doIf(session => session.contains(Constants.RESP_AUTH_TOKEN) && session.contains(Constants.RESP_SECURITY_TOKEN))
      {
        exec(GetListRequest.getUserListRequest)
          .doIf(session => session.contains(Constants.RESP_GET_MY_LIST)) {
            exec(myListDistribution)
          }

      }
}

