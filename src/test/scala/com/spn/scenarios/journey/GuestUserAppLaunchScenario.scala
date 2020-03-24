package com.spn.scenarios.journey

import com.jayway.jsonpath._
import com.spn.common.{CommonFeedFiles, Constants}
import com.spn.requests.{GetInitialConfigRequest, GetPageIdRequest, GetTokenRequest, GetULDRequest}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.concurrent.duration._
import scala.util.Random

object GuestUserAppLaunchScenario  {

  val guestUserAppLaunchScenario = scenario("Guest User App Launch Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(CommonFeedFiles.dataFeederProperty)

    .group("App Launch - Guest User") {
      exec(GetTokenRequest.getToken) // TODO This section needs a revisit as the security token implementation is changed
        .doIf(session => session.contains(Constants.RESP_TOKEN)) {

          def setRandomPageURLToSession(session: Session, searchString: String) : Session = {

            var expression = "$.menu.containers[?(@.metadata.url_path == '" + searchString + "' )].actions[?(@.targetType== 'PAGE')].uri"
            println(s"\nExpression : $expression")

            val respInitialConfig = session(Constants.RESP_INITIAL_CONFIG).as[String]
            println(s"\nrespInitialConfig : $respInitialConfig")

            var context = JsonPath.parse(respInitialConfig)
            var pageURLFound = context.read[JSONArray](expression)
            println(s"\nPage URL Found : $pageURLFound")

            // Primary expression didn't give any result, so fallback on the new expression
            if(pageURLFound == null || pageURLFound.isEmpty) {
              // Fallback mechanism
              expression = "$.menu.containers[*].items[?(@.metadata.url_path == '" + searchString + "')].actions[?(@.targetType== 'PAGE')].uri"
              println(s"\nFallback Expression : $expression")

              context = JsonPath.parse(respInitialConfig)
              pageURLFound = context.read[JSONArray](expression)
              println(s"\nPage URL Found (Fallback) : $pageURLFound")
            }

            // Cherry picking a url to navigate to
            var finalSelectedPageToNavigateTo = ""
            if(pageURLFound != null && pageURLFound.size() == 1) {
              finalSelectedPageToNavigateTo = pageURLFound.get(0).toString
            } else if(pageURLFound != null && pageURLFound.size() > 1) {
              val size = pageURLFound.size()
              println(s"\nPage URL Found (Size) : $size")
              finalSelectedPageToNavigateTo = pageURLFound.get(Random.nextInt(size-1)).toString
            }

            println(s"\nFinal selected Page to Navigate To for '$searchString' is : $finalSelectedPageToNavigateTo")
            session.set(Constants.RESP_RANDOM_PAGE_URL,finalSelectedPageToNavigateTo)
          }

          val openHomePage = exec(session => {
            setRandomPageURLToSession(session, "home") // Where we are getting and setting Movie URL
          }).exec(GetPageIdRequest.PageId)

          val openMoviesPage = exec(session => {
            setRandomPageURLToSession(session, "movies") // Where we are getting and setting Movie URL
          }).exec(GetPageIdRequest.PageId)

          val openTVShowsPage = exec(session => {
            setRandomPageURLToSession(session, "shows") // Where we are getting and setting TV Shows URL
          }).exec(GetPageIdRequest.PageId)

          exec(session => {
            val getSecurityToken = session(Constants.RESP_TOKEN).as[String]
            println(s"\nRESP_SECURITY_TOKEN is: $getSecurityToken")
            session
          })

            .pause(1, 3 seconds)
            .exec(GetInitialConfigRequest.getInitialConfig)
            .pause(1, 3 seconds)
            .exec(openHomePage) // Definitely invoke Home Page (as that is where user lands)
            .pause(1, 3 seconds)
            .exec(GetULDRequest.getULD)
            .pause(1, 3 seconds)
            .randomSwitch(
              50d -> openMoviesPage,
              50d -> openTVShowsPage)
        }
    }
}





