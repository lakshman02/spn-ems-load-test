package com.spn.scenarios.journey

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import com.jayway.jsonpath.JsonPath
import com.spn.common.{CommonFeedFiles, Constants}
import com.spn.requests.{GetInitialConfigRequest, GetPageIdRequest, GetTokenRequest, GetULDRequest}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray
// import org.json.{JSONArray, JSONObject}

import scala.concurrent.duration._
import scala.util.parsing.json.JSON

object GuestUserAppLaunchScenario {

  val guestUserAppLaunchScenario = scenario("Guest User App Launch Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)

    .group("App Launch - Guest User"){
      exec(GetTokenRequest.getToken)
        .doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)){
          exec(session => {
            val getSecurityToken = session(Constants.RESP_SECURITY_TOKEN).as[String]
            println(s"\nRESP_SECURITY_TOKEN is: $getSecurityToken")

            session
          })
            .pause(1, 3 seconds)
            .exec(GetInitialConfigRequest.getInitialConfig)
            .exec(session => {
              val initialConfigResponse = session(Constants.RESP_INITIAL_CONFIG).as[String]
              println(s"\nRESP_INITIAL_CONFIG : $initialConfigResponse")


/*              val nObject = new JSONObject(initialConfigResponse);
              val allContainersUnderMenu = nObject.getJSONObject("menu").getJSONArray("containers");
              println(s"\nallContainersUnderMenu : $allContainersUnderMenu")*/

              val context = JsonPath.parse(initialConfigResponse)
              val pageValue = context.read[JSONArray]("$.*.containers[*].actions[?(@.targetType == 'PAGE')].uri")

            //  val pageValue = context.read[JSONArray]("$.*.containers[*].metadata[?(@.label == 'Home').actions[?(@.targetType == 'PAGE')].uri").toString

             // val labelValue = context.read[JSONArray]("$.*.containers[*].metadata[?(@.label == 'Home')")
           //  val labelValue = context.read[JSONArray]("$.*.containers[*].metadata.label").get(0)
            //  val labelValuesSize = labelValue.size()

//              println(s"$labelValue")
//
//              if(labelValue.equals("Home")){
//                println("Home Is Here")
//              }
             val RANDOM_PAGE_URL = pageValue.get(0).toString

              println(s"\nvalue : $RANDOM_PAGE_URL")

              //              val value = context.read("$.*.containers[*].actions[?(@.targetType == 'PAGE')].uri")
//              println(s"\nparsedValueExtracted : $parsedValueExtracted")

              session

            })
            .pause(1, 3 seconds)
            .exec(GetPageIdRequest.PageId) // Definitly invoke Home Page
//            .pause(1, 3 seconds)
//            .randomSwitch(
//              80d -> exec(GetTokenRequest.getToken),
//              20d -> exec(GetULDRequest.getULD))
//            .pause(1, 3 seconds)
//            .exec(GetULDRequest.getULD)
        }
    }
}





