package com.spn.config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object Config {
  //baseurl
  val app_url = "https://apiqa.sonyliv.com/"

  //api urls
  val URL_INITIAL_CONFIG = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/INITIAL/CONFIG?"
  val GET_MENU_URL="/${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/MENU/menu";

  val users = Integer.getInteger("users", 2).toInt
  val duration = Integer.getInteger("duration", 10).toInt
  val throughput = Integer.getInteger("throughput", 1 ).toInt
  val defaultResponseTime=Integer.getInteger("responseTime", 1).toInt

  //http protocol configuration
  val httpProtocol = http
    .baseUrl(app_url )
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")
}

