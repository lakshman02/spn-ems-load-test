package com.spn.scenarios.journey

import com.jayway.jsonpath._
import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.{GetInitialConfigRequest, GetPageIdRequest, GetTokenRequest, GetULDRequest}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object GuestUserAppLaunchScenario  {

  val channelFeederOverride = Array(
    Map("channel" -> "IPHONE"),
    Map("channel" -> "IPAD"),
    Map("channel" -> "ANDROID_PHONE"),
    Map("channel" -> "ANDROID_TAB"),
//    Map("channel" -> "APPLE_TV"),
//    Map("channel" -> "FIRE_TV"),
//    Map("channel" -> "SONY_ANDROID_TV"),
//    Map("channel" -> "XIAOMI_ANDROID_TV"),
//    Map("channel" -> "JIO_ANDROID_TV"),
//    Map("channel" -> "SONY_HTML_TV"),
//    Map("channel" -> "SAMSUNG_HTML_TV"),
//    Map("channel" -> "JIO_KIOS"),
    Map("channel" -> "WEB"),
    Map("channel" -> "IOS")
  ).random

  val guestUserAppLaunchScenario = scenario("Guest User App Launch Scenario")
    .feed(CommonFeedFiles.dataFeederTenant)
    .feed(CommonFeedFiles.dataFeederCluster)
    .feed(CommonFeedFiles.dataFeederLocale)
//    .feed(CommonFeedFiles.dataFeederChannel)
    .feed(channelFeederOverride)
    .feed(CommonFeedFiles.dataFeederProperty)

    .group("App Launch - Guest User - Channel - ${channel}") {
      exec(ApiSecurity.getToken)
        .doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {

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

            if(finalSelectedPageToNavigateTo != null && !finalSelectedPageToNavigateTo.isEmpty) {
              session.set(Constants.RESP_RANDOM_PAGE_URL,finalSelectedPageToNavigateTo)
            } else {
              println(s"\nAll attempts failed, do a final Fall Back for '$searchString'")
              session
            }
          }

          val openHomePage = exec(session => {
            // Where we are getting and setting Home URL
            setRandomPageURLToSession(session, "home")
              .set("pageSuffix", "Home Landing - Pagination:0-5")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          val openSearchPage = exec(session => {
            // Where we are getting and setting Search URL
            setRandomPageURLToSession(session, "search")
              .set("pageSuffix","Search")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          val openSportsPage = exec(session => {
            // Where we are getting and setting Sports URL
            setRandomPageURLToSession(session, "sports")
              .set("pageSuffix","Sports - Pagination:5-10")
              .set("paginationFrom", "5")
              .set("paginationTo", "10")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          // Movie related
          val openMoviesPageDefault = exec(session => {
            // Where we are getting and setting Movie URL
            setRandomPageURLToSession(session, "movies")
              .set("pageSuffix","Movies Landing - Pagination:0-5")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          val openActionMovies = exec(session => {
            // Where we are getting and setting Action Movie URL
            setRandomPageURLToSession(session, "actionmovies")
              .set("pageSuffix","Action Movies - Pagination:0-5")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          val openDramaMovies = exec(session => {
            // Where we are getting and setting Drama Movie URL
            setRandomPageURLToSession(session, "dramamovies")
              .set("pageSuffix","Drama Movies - Pagination:0-5")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          // Movies Page
          val openMoviesPage = randomSwitch(
            50d -> openMoviesPageDefault,
            25d -> openActionMovies,
            25d -> openDramaMovies
          )

          // Show related
          val openTVShowsPageDefault = exec(session => {
            // Where we are getting and setting TV Shows URL
            setRandomPageURLToSession(session, "shows")
              .set("pageSuffix","TV Shows Landing - Pagination:0-5")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          val openTVShowsPageSabShows = exec(session => {
            // Where we are getting and setting Sab TV Shows URL
            setRandomPageURLToSession(session, "sabshows")
              .set("pageSuffix","TV Shows - SAB Shows - Pagination:0-5")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          val openTVShowsPageSetShows = exec(session => {
            // Where we are getting and setting Set TV Shows URL
            setRandomPageURLToSession(session, "setshows")
              .set("pageSuffix","TV Shows - SET Shows - Pagination:0-5")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          // TV Shows
          val openTVShowsPage = randomSwitch(
            50d -> openTVShowsPageDefault,
            25d -> openTVShowsPageSabShows,
            25d -> openTVShowsPageSetShows
          )

          // Channels related
          val openChannelsPageDefault = exec(session => {
            // Where we are getting and setting Channels URL
            setRandomPageURLToSession(session, "channels")
              .set("pageSuffix","Channels Landing - Pagination:0-5")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          val openEntertainmentChannels = exec(session => {
            // Where we are getting and setting Entertainment URL
            setRandomPageURLToSession(session, "entertainmentchannels")
              .set("pageSuffix","Channels - Entertainment - Pagination:0-5")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          val openNewsChannels = exec(session => {
            // Where we are getting and setting News URL
            setRandomPageURLToSession(session, "newschannels")
              .set("pageSuffix","Channels - News - Pagination:0-5")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          val openInfotainmentChannels = exec(session => {
            // Where we are getting and setting Infotainment URL
            setRandomPageURLToSession(session, "infotainmentchannels")
              .set("pageSuffix","Channels - Infotainment - Pagination:0-5")
              .set("paginationFrom", "0")
              .set("paginationTo", "5")
          }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
            exec(GetPageIdRequest.PageId)
          }

          // Channels
          val openChannelsPage = randomSwitch(
            40d -> openChannelsPageDefault,
            25d -> openEntertainmentChannels,
            25d -> openNewsChannels,
            10d -> openInfotainmentChannels
          )

          exec(GetInitialConfigRequest.getInitialConfig)
            .exec(openHomePage) // Definitely invoke Home Page (as that is where user lands)
            .exec(GetULDRequest.getULD)
            .randomSwitch(
              5d -> openSearchPage,
              10d -> openTVShowsPage,
              30d -> openMoviesPage,
              30d -> openSportsPage,
              25d -> openChannelsPage)
        }
    }
}





