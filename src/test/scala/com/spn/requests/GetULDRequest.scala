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

//    Random r = new Random();
//    Integer prefix;
//    while ({
//      prefix = r.nextInt(256);
//      prefix in [0,10,100,127,172,192,198,203,224,240,255]
//    }());
//    return prefix + "." + r.nextInt(256) + "." + r.nextInt(256) + "." + r.nextInt(256);
  }

  val getULD = exec(http("GetULDRequest")
    .get(Config.app_url + Config.GET_ULD_URL)
      .headers(ipHeaders)
    .check(status.in(200,300))
  )
}
