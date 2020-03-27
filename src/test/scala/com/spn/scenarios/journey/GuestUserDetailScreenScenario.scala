package com.spn.scenarios.journey

import com.jayway.jsonpath.JsonPath
import com.spn.common.{CommonFeedFiles, Constants}
import com.spn.requests.VODDetailsRequest
import com.spn.scenarios.groups.UserAppLaunchScenario
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object GuestUserDetailScreenScenario {

  val channelFeederOverride = Array(
    Map("channel" -> "IPHONE"),
    Map("channel" -> "IPAD"),
    Map("channel" -> "ANDROID_PHONE"),
    //    Map("channel" -> "ANDROID_TAB"),
    Map("channel" -> "APPLE_TV"),
    Map("channel" -> "FIRE_TV")
    //    Map("channel" -> "SONY_ANDROID_TV"),
    //    Map("channel" -> "XIAOMI_ANDROID_TV"),
    //    Map("channel" -> "JIO_ANDROID_TV"),
    //    Map("channel" -> "SONY_HTML_TV"),
    //    Map("channel" -> "SAMSUNG_HTML_TV"),
    //    Map("channel" -> "JIO_KIOS"),
    //    Map("channel" -> "WEB"),
    //    Map("channel" -> "IOS")
  ).random

  val guestUserDetailScreenScenario = scenario("Guest User Detail Screen Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    //    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)

    .group("Detail Screen - Guest User - Channel - ${channel}") {
      exec(session => session.set(Constants.REQ_USER_TYPE, Constants.USER_TYPE_GUEST))
      exec(UserAppLaunchScenario.userAppLaunchScenario)


      def setTheUrlIdToSession(session: Session, contentType: String, contentSubtype: String): Session = {

        val pageResponse = session(Constants.RESP_PAGE_RESPONSE).as[String]
        println(s"\npageResponse : $pageResponse")


        val expression = "$.containers[*].assets.containers[?(@.metadata.contentType == '"+contentType+"')].id"
        println(s"\nExpression : $expression")

        val context = JsonPath.parse(pageResponse)
        val contentIdFound = context.read[JSONArray](expression)



        //        var subContentExpression = "$.containers[*].assets.containers[?(@.metadata.contentType == 'VOD' && @.metadata.contentSubtype == 'SHOW')].id"
        //        println(s"\nExpression : $subContentExpression")
        //
        //        var subContentIdFound = context.read[JSONArray](subContentExpression)

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
        randomSwitch(90d -> openVODDetails)


    }
}


