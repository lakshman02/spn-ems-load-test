package com.spn.scenarios.groups

import com.jayway.jsonpath._
import com.spn.common.{ApiSecurity, CommonFeedFiles, Constants}
import com.spn.requests.{GetInitialConfigRequest, GetPageIdRequest, GetProfileRequest, GetTokenRequest, GetULDRequest, LoginWithEmailRequest,UpdateProfileRequest,AccountSearchRequest,GenerateDeviceActivationCodeRequest,RegisterDeviceRequest}
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object UserAppLaunchScenario  {

  // ALL the functions goes here - starts
  def setRandomPageURLToSession(session: Session, urlPath: String, fallBackLabel: String) : Session = {

    var expression = "$.menu.containers[?(@.metadata.url_path == '" + urlPath + "')].actions[?(@.targetType== 'PAGE')].uri"
    println(s"\nExpression : $expression")

    val respInitialConfig = session(Constants.RESP_INITIAL_CONFIG).as[String]
    println(s"\nrespInitialConfig : $respInitialConfig")

    var context = JsonPath.parse(respInitialConfig)
    var pageURLFound = context.read[JSONArray](expression)
    println(s"\nPage URL Found : $pageURLFound")

    // Primary expression didn't give any result, so fallback on the new expression
    if(pageURLFound == null || pageURLFound.isEmpty) {
      // Fallback mechanism
      expression = "$.menu.containers[*].items[?(@.metadata.url_path == '" + urlPath + "')].actions[?(@.targetType== 'PAGE')].uri"
      println(s"\nFallback Expression : $expression")

      context = JsonPath.parse(respInitialConfig)
      pageURLFound = context.read[JSONArray](expression)
      println(s"\nPage URL Found (Fallback) : $pageURLFound")
    }

    // Final fall back for lookup, if this fails - no point forward
    if(pageURLFound == null || pageURLFound.isEmpty) {
      expression = "$.menu.containers[?(@.metadata.label == '" + fallBackLabel + "')].actions[?(@.targetType== 'PAGE')].uri"
      println(s"\nFinal fallback Expression : $expression")

      context = JsonPath.parse(respInitialConfig)
      pageURLFound = context.read[JSONArray](expression)
      println(s"\nPage URL Found (Final Fallback) : $pageURLFound")
    }

    // Cherry picking a url to navigate to
    var finalSelectedPageToNavigateTo = ""
    if(pageURLFound != null && pageURLFound.size() == 1) {
      finalSelectedPageToNavigateTo = pageURLFound.get(0).toString
    } else if(pageURLFound != null && pageURLFound.size() > 1) {
      val size = pageURLFound.size()
      finalSelectedPageToNavigateTo = pageURLFound.get(Random.nextInt(size-1)).toString
    }

    println(s"\nFinal selected Page to Navigate To for '$urlPath' is : $finalSelectedPageToNavigateTo")

    if(finalSelectedPageToNavigateTo != null && !finalSelectedPageToNavigateTo.isEmpty) {
      session.set(Constants.RESP_RANDOM_PAGE_URL,finalSelectedPageToNavigateTo.replaceFirst("/",""))
    } else {
      println(s"\nAll attempts failed, for '$urlPath' & '$fallBackLabel'")
      session
    }
  }

  val openHomePage = exec(session => {
    // Where we are getting and setting Home URL
    setRandomPageURLToSession(session, "home", "Home")
      .set("pageSuffix", "Home Landing - p[0-5]")
      .set("paginationFrom", "0")
      .set("paginationTo", "5")
  }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(GetPageIdRequest.PageId)
  }

  val openSearchPage = exec(session => {
    // Where we are getting and setting Search URL
    setRandomPageURLToSession(session, "search", "Search")
      .set("pageSuffix","Search")
      .set("paginationFrom", "0")
      .set("paginationTo", "5")
  }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(GetPageIdRequest.PageId)
  }

  val openSportsPage = exec(session => {
    // Where we are getting and setting Sports URL
    setRandomPageURLToSession(session, "sports", "Sports")
      .set("pageSuffix","Sports - p[0-5]")
      .set("paginationFrom", "0")
      .set("paginationTo", "5")
  }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(GetPageIdRequest.PageId)
  }

  // Movie related
  val openMoviesPageDefault = exec(session => {
    // Where we are getting and setting Movie URL
    setRandomPageURLToSession(session, "movies", "Movies")
      .set("pageSuffix","Movies Landing - p[0-5]")
      .set("paginationFrom", "0")
      .set("paginationTo", "5")
  }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(GetPageIdRequest.PageId)
  }

  val openActionMovies = exec(session => {
    // Where we are getting and setting Action Movie URL
    setRandomPageURLToSession(session, "actionmovies", "Movies")
      .set("pageSuffix","Action Movies - p[5-10]")
      .set("paginationFrom", "5")
      .set("paginationTo", "10")
  }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(GetPageIdRequest.PageId)
  }

  val openDramaMovies = exec(session => {
    // Where we are getting and setting Drama Movie URL
    setRandomPageURLToSession(session, "dramamovies", "Movies")
      .set("pageSuffix","Drama Movies - p[5-10]")
      .set("paginationFrom", "5")
      .set("paginationTo", "10")
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
    setRandomPageURLToSession(session, "shows", "TV Shows")
      .set("pageSuffix","TV Shows Landing - p[0-5]")
      .set("paginationFrom", "0")
      .set("paginationTo", "5")
  }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(GetPageIdRequest.PageId)
  }

  val openTVShowsPageSabShows = exec(session => {
    // Where we are getting and setting Sab TV Shows URL
    setRandomPageURLToSession(session, "sabshows", "TV Shows")
      .set("pageSuffix","TV Shows - SAB Shows - p[5-10]")
      .set("paginationFrom", "5")
      .set("paginationTo", "10")
  }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(GetPageIdRequest.PageId)
  }

  val openTVShowsPageSetShows = exec(session => {
    // Where we are getting and setting Set TV Shows URL
    setRandomPageURLToSession(session, "setshows", "TV Shows")
      .set("pageSuffix","TV Shows - SET Shows - p[5-10]")
      .set("paginationFrom", "5")
      .set("paginationTo", "10")
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
    setRandomPageURLToSession(session, "channels", "Channels")
      .set("pageSuffix","Channels Landing - p[0-5]")
      .set("paginationFrom", "0")
      .set("paginationTo", "5")
  }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(GetPageIdRequest.PageId)
  }

  val openEntertainmentChannels = exec(session => {
    // Where we are getting and setting Entertainment URL
    setRandomPageURLToSession(session, "entertainmentchannels", "Channels")
      .set("pageSuffix","Channels - Entertainment - p[5-10]")
      .set("paginationFrom", "5")
      .set("paginationTo", "10")
  }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(GetPageIdRequest.PageId)
  }

  val openNewsChannels = exec(session => {
    // Where we are getting and setting News URL
    setRandomPageURLToSession(session, "newschannels", "Channels")
      .set("pageSuffix","Channels - News - p[5-10]")
      .set("paginationFrom", "5")
      .set("paginationTo", "10")
  }).doIf(session => session.contains(Constants.RESP_RANDOM_PAGE_URL)){
    exec(GetPageIdRequest.PageId)
  }

  val openInfotainmentChannels = exec(session => {
    // Where we are getting and setting Infotainment URL
    setRandomPageURLToSession(session, "infotainmentchannels", "Channels")
      .set("pageSuffix","Channels - Infotainment - p[5-10]")
      .set("paginationFrom", "5")
      .set("paginationTo", "10")
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
  // ALL the functions goes here - ends

  // App launch User Journey goes here - starts
  val userAppLaunchScenario = doIf(session => session.contains(Constants.RESP_SECURITY_TOKEN)) {
        exec(GetInitialConfigRequest.getInitialConfig)
          .exec(GetULDRequest.getULD)
          .exec(openHomePage) // Definitely invoke Home Page (as that is where user lands)
          .doIf(session => session.contains(Constants.REQ_USER_TYPE) && session(Constants.REQ_USER_TYPE).as[String].equals(Constants.USER_TYPE_LOGGED_IN)) {
            exec(GetProfileRequest.getProfile)
          }
      }

  // App launch User Journey goes here - ends
}

