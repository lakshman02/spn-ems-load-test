package com.spn.config


object Config {
  val app_url = "https://apiqa.sonyliv.com/"

  val URL_INITIAL_CONFIG = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/INITIAL/CONFIG?"

  val users = Integer.getInteger("users", 1).toInt
  val rampUp = Integer.getInteger("rampup", 300).toInt
  val duration = Integer.getInteger("duration",300).toInt
  val throughput = Integer.getInteger("throughput", 1).toInt
  val times = Integer.getInteger("times", 10).toInt
  val eachLevelLasting = Integer.getInteger("eachLevelLasting", 10).toInt
  val separatedByRampsLasting = Integer.getInteger("separatedByRampsLasting", 10).toInt
  val startingFrom = Integer.getInteger("startingFrom", 10).toInt
}


