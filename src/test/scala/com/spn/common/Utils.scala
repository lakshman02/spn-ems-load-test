package com.spn.common

import io.gatling.core.Predef.Session

object Utils {

  def checkIfTVPlatform(session: Session) = {
    session.contains("channel") && (// Do this only for TV platforms
      session("channel").as[String].equals("APPLE_TV")
        || session("channel").as[String].equals("FIRE_TV")
        || session("channel").as[String].equals("SONY_ANDROID_TV")
        || session("channel").as[String].equals("XIAOMI_ANDROID_TV")
        || session("channel").as[String].equals("JIO_ANDROID_TV")
        || session("channel").as[String].equals("SONY_HTML_TV")
        || session("channel").as[String].equals("SAMSUNG_HTML_TV")
      )
  }
}

