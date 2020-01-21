package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import io.gatling.http.Predef._

object GetInitialConfigRequest {

  //val sentHeaders = Map("Authorization" -> "bearer ${token}")

  val getInitialConfig = exec(http("GetInitialConfigRequest")
   .get(Config.app_url + "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/INITIAL/CONFIG?"))
//    .get(Config.app_url)
//    .check(status is 200)



 // val get_token = http("RequestName").get(app_url + "/token")
  //  .check(status is 200)
   // .check(jsonPath("$..token").saveAs("token"))
}