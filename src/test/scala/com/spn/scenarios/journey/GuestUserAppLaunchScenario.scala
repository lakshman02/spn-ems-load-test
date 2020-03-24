package com.spn.scenarios.journey

import com.jayway.jsonpath._
import com.spn.common.{CommonFeedFiles, Constants}
import com.spn.requests.{GetInitialConfigRequest, GetPageIdRequest, GetTokenRequest, GetULDRequest}
import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import net.minidev.json.JSONArray

import scala.concurrent.duration._

object GuestUserAppLaunchScenario  {

//  var movie_function: ChainBuilder = _
//  var tv_show_function: ChainBuilder = _




  def functionMovie(session: Session) : ChainBuilder = {
    val randomMovieUrl = JsonPath.parse(session("initialConfigResponse"))
      .read[JSONArray]("$.*.containers[?(@.metadata.label== 'Movies')].actions[?(@.targetType== 'PAGE')].uri").get(0).toString

    println(s"\nMOVIE URL : $randomMovieUrl")

   // exec(session.set(Constants.RESP_RANDOM_PAGE_URL,s"$randomMovieUrl"))
    return exec(GetPageIdRequest.PageId)
  }

  def functionTVShows() : ChainBuilder = {
    val randomTVUrl = JsonPath.parse(initialConfigResponse)
      .read[JSONArray]("$.*.containers[?(@.metadata.label== 'TV Shows')].actions[?(@.targetType== 'PAGE')].uri").get(0).toString

    println(s"\nTV SHOW URL : $randomTVUrl")

    exec(session.set(Constants.RESP_RANDOM_PAGE_URL,s"$randomTVUrl"))
      .exec(GetPageIdRequest.PageId)
  }



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

              val randomPageUrl = JsonPath.parse(initialConfigResponse)
                .read[JSONArray]("$.*.containers[?(@.metadata.label== 'Home')].actions[?(@.targetType== 'PAGE')].uri").get(0).toString


             // functionMovie()

             // functionTVShows()

              println(s"\nPage URL : $randomPageUrl")

              session.set(Constants.RESP_RANDOM_PAGE_URL,s"$randomPageUrl")

//              session.set(s"$movie_function",functionMovie)
//              session.set(s"$tv_show_function",functionTVShows)

            })
            .pause(1, 3 seconds)
            .exec(GetPageIdRequest.PageId) // Definitely invoke Home Page
            .pause(1, 3 seconds)
            .exec(GetULDRequest.getULD)
            .pause(1, 3 seconds)
            .randomSwitch(
              80d -> exec(session => {

                val randomMovieUrl = JsonPath.parse(session("initialConfigResponse"))
                  .read[JSONArray]("$.*.containers[?(@.metadata.label== 'Movies')].actions[?(@.targetType== 'PAGE')].uri").get(0).toString

                println(s"\nMOVIE URL : $randomMovieUrl")

                // exec(session.set(Constants.RESP_RANDOM_PAGE_URL,s"$randomMovieUrl"))

              }

              ).exec(GetPageIdRequest.PageId),
              20d -> tv_show_function)
        }

    }

}





