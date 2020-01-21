package com.spn.config


object Config {
  val app_url = "https://apiqa.sonyliv.com/"

  val URL_INITIAL_CONFIG = "${tenant}/1.4/${cluster}/${locale}/${channel}/${propertyName}/INITIAL/CONFIG?"

  val users = Integer.getInteger("users", 10).toInt
  val rampUp = Integer.getInteger("rampup", 1).toInt
  val throughput = Integer.getInteger("throughput", 1 ).toInt
}
