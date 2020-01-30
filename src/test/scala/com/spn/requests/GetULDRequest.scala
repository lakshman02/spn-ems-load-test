package com.spn.requests

import com.spn.config.Config
import io.gatling.core.Predef._
import scala.util.Random
import io.gatling.http.Predef._

object GetULDRequest {

  val ipHeaders = Map("x-forwarded-for" -> randomIp)

  private def randomIp: String = {

    val r = Random
    r.nextInt(255) + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256)
  }

  val getULD = exec(http("Get ULD Request")
    .get(Config.app_url + Config.GET_ULD_URL)
      .headers(ipHeaders)
    .check(status.in(200,300))
  )
}
