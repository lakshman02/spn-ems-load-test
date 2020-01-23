package com.spn.requests

  import com.spn.config.Config
  import io.gatling.core.Predef._
  import io.gatling.http.Predef._
  object LoginRequest {

    val sentHeaders = Map("x-via-device" -> "true")
    val LoginRequest = exec(http("USER LOGIN ")
      .post(Config.app_url + Config.Login_URL)
      .headers(sentHeaders)
      .body(StringBody ("""{
             "mobileNumber": "${mobileNumber}",
              "password": "${password}",
               "rememberMe": true,
             "appClientId": "${appClientId}",
              "channelPartnerID":"MSMIND",
            "timestamp": "2020-01-03T05:22:49.959Z",
            "deviceType":"${deviceType}",
            "serialNo": ""
        }""")).asJson
      .check(jsonPath("$.resultCode").is("OK")))
  }
          /*"""{
              "mobileNumber": "${mobileNumber}",
              "password": "${password}",
               "rememberMe": true,
             "appClientId": "${appClientId}",
              "channelPartnerID":"MSMIND",
            "timestamp": "2020-01-03T05:22:49.959Z", //Todo Should be generated dynamicaly
            "deviceType":"${deviceType}",
            "serialNo": ""
            }"""*/

//  "mobileNumber": "9052227607",
//            "password": "Test@1234",
//            "rememberMe": true,
//            "appClientId": "1212475532.1575468358",
//            "channelPartnerID": "MSMIND",
//            "timestamp": "2020-01-03T05:22:49.959Z",
//            "deviceType": "android",
//            "serialNo": ""






    //val login = exec(http("loginRequest")
     // .post(Config.app_url + Config.Login_URL)
     // .check(status is 200))
      //.check(jsonPath("$.resultCode").is("OK")))
//      .exec{
//        session =>
//          println(session)
//          session
//      }
//      .pause(1)
