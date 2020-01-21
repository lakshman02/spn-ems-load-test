package test.scala.com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetMenuRequest {
  val getMenu= http("Get Menu")
    .get(Config.app_url + Config.GET_MENU_URL)
    .check(status is 200)
}
