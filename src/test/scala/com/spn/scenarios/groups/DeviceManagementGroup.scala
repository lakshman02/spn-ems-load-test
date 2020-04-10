package com.spn.scenarios.groups

import com.jayway.jsonpath.JsonPath
import com.spn.common.Constants
import com.spn.requests._
import com.spn.scenarios.groups.PageDetailScreen.setTheUrlIdToSession
import io.gatling.core.Predef._
import net.minidev.json.JSONArray

import scala.util.Random

object DeviceManagementGroup {

  def setSerialNoSession(session: Session): Session = {

    val getDeviceResponse = session(Constants.RESP_GET_DEVICE_COMPLETE_RESPONSE).as[String]
    println(s"\ngetDeviceResponse : $getDeviceResponse")

    var expression = ""

    expression = "$.[?(@.serialNo =~ /d6acc46e-5a09-d432-1afb.*/)].serialNo"
    println(s"\nExpression : $expression")

    val context = JsonPath.parse(getDeviceResponse)
    val serialNum = context.read[JSONArray](expression)

    // Cherry picking a url to navigate to
    var finalserialNumNavigateTo = ""
    if (serialNum != null && serialNum.size() == 1) {
      finalserialNumNavigateTo = serialNum.get(0).toString
    }
    else if (serialNum != null && serialNum.size() > 1) {
      val size = serialNum.size()
      finalserialNumNavigateTo = serialNum.get(Random.nextInt(size - 1)).toString
    }

    println(s"\nFinal serialNum to Navigate To for  is : $finalserialNumNavigateTo")

    if (finalserialNumNavigateTo != null && !finalserialNumNavigateTo.isEmpty) {
      session.set(Constants.RESP_DEVICE_SERIAL_NUMBER, finalserialNumNavigateTo)
    }
    else {
      print("Did not find serialNum")
      session
    }
  }

  val openRemoveDevice = exec(session =>{
    setSerialNoSession(session)
  }).doIf(session => session.contains(Constants.RESP_DEVICE_SERIAL_NUMBER)) {
    exec(RemoveDevicesRequest.removeDevicesRequest)
  }

  val doDeviceManagementOperations = doIf(session => session.contains(Constants.RESP_AUTH_TOKEN)) {
    group("Device Management - Logged-In User - Channel - ${channel}"){
        exec(GetDevicesRequest.getDevicesRequest)
        .exec(openRemoveDevice)
    }
  }
}