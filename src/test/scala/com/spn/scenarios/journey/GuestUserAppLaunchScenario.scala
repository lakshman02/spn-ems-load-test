package com.spn.scenarios.journey

import com.jayway.jsonpath.{DocumentContext, JsonPath}
import com.spn.common.{CommonFeedFiles, Constants}
import com.spn.requests.{GetInitialConfigRequest, GetPageIdRequest, GetTokenRequest, GetULDRequest}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.concurrent.duration._

object GuestUserAppLaunchScenario {


  val guestUserAppLaunchScenario = scenario("Guest User App Launch Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)

    .group("App Launch - Guest User") {
      exec(GetTokenRequest.getToken)
        .doIf(session => session.contains(Constants.RESP_TOKEN)) {
          exec(session => {
            val getSecurityToken = session(Constants.RESP_TOKEN).as[String]
            println(s"\nRESP_SECURITY_TOKEN is: $getSecurityToken")

            session
          })
            .pause(1, 3 seconds)
            .exec(GetInitialConfigRequest.getInitialConfig)
            .exec(session => {
              val initialConfigResponse = session(Constants.RESP_INITIAL_CONFIG).as[String]
              println(s"\nRESP_INITIAL_CONFIG : $initialConfigResponse")

              val context = JsonPath.parse(initialConfigResponse)

              val randomPageUrl = context
                .read[JSONArray]("$.*.containers[?(@.metadata.label== 'Home')].actions[?(@.targetType== 'PAGE')].uri").get(0).toString


              session.set(Constants.RESP_RANDOM_PAGE_URL,s"$randomPageUrl")

              println(s"\nPage URL : $randomPageUrl")
              session

            })
            .pause(1, 3 seconds)
            .exec(GetPageIdRequest.PageId) // Definitely invoke Home Page
//            .pause(1, 3 seconds)
//            .randomSwitch(
//              80d -> exec(GetTokenRequest.getToken),
//              20d -> exec(GetULDRequest.getULD))
//            .pause(1, 3 seconds)
//            .exec(GetULDRequest.getULD)
        }

    }

}





