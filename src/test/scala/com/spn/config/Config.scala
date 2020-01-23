package com.spn.config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Config {
  //Baseurl
  val app_url = "https://apiqa.sonyliv.com/"

  //Api urls
  val URL_INITIAL_CONFIG = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/INITIAL/CONFIG?"
  val GET_MENU_URL="/${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/MENU/menu"
  val GET_PageID = "/${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/PAGE/${pageID}"

  val users = Integer.getInteger("users", 1).toInt
  val rampUp = Integer.getInteger("rampup", 5).toInt
  val duration = Integer.getInteger("duration",1).toInt
  val throughput = Integer.getInteger("throughput", 1).toInt
  val times = Integer.getInteger("times", 5).toInt
  val eachLevelLasting = Integer.getInteger("eachLevelLasting", 5).toInt
  val separatedByRampsLasting = Integer.getInteger("separatedByRampsLasting", 5).toInt
  val startingFrom = Integer.getInteger("startingFrom", 5).toInt
  val defaultResponseTime=Integer.getInteger("responseTime", 1).toInt

  //http protocol configuration
  val httpProtocol = http
    .baseUrl(app_url )
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

}

