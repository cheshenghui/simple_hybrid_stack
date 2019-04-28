package com.oceanus.simple_hybrid_stack

import android.app.Activity
import com.oceanus.flutter_plugin_simple_open.Routers
import io.flutter.app.FlutterActivity
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar

class SimpleHybridStackPlugin : MethodCallHandler {

  private var act: Activity? = null
  private var routeName: String = ""
  private var args: HashMap<String, Any>? = null
  private lateinit var channel: MethodChannel

  companion object {

    private var _instance: SimpleHybridStackPlugin? = null

    @JvmStatic
    fun registerWith(registrar: Registrar) {
      _instance?.channel = MethodChannel(registrar.messenger(), "simple_hybrid_stack")
      _instance?.channel?.setMethodCallHandler(_instance)

    }

    @JvmStatic
    fun init(activity: FlutterActivity, route: String, arg: HashMap<String, Any>?) {
      if (_instance == null) _instance = SimpleHybridStackPlugin()
      _instance?.act = activity
      _instance?.routeName = route
      _instance?.args = arg
      _instance?.channel = MethodChannel(activity.flutterView, "simple_hybrid_stack")
      _instance?.channel?.setMethodCallHandler(_instance)
    }

    @JvmStatic
    fun addRoute(routeName: String, clazz: Class<*>) {
      Routers.addRoute(routeName, clazz)
    }
  }

  override fun onMethodCall(call: MethodCall, result: Result) {
    if (call.method == "getPlatformVersion") {
      result.success("Android ${android.os.Build.VERSION.RELEASE}")
    } else if (call.method == "initFlutterPage") {
      //    call.arguments
      val argsMap = mutableMapOf<String, Any?>()
      argsMap["routeName"] = routeName
      argsMap["args"] = args
      result.success(argsMap)
    } else if (call.method == "openNativePage") {
      if (call.arguments != null) {
        val flutterArgs = call.arguments as HashMap<*, *>
        openNativePage(act!!, flutterArgs, result)
      }
    } else {
      result.notImplemented()
    }
  }

  private fun openNativePage(act: Activity, flutterArgs: Map<*, *>, result: Result) {
    val actName = flutterArgs["routeName"] as String
    var args: HashMap<*, *>? = null
    if (flutterArgs["args"] != null)
      args = flutterArgs["args"] as HashMap<*, *>
    Routers.startAct(act, actName, args)
    result.success(1)
  }

//    //    //////// Android 调用 Flutter //////////
//    private fun flutterMethod(methodName: String, args: Map<*, *>) {
//        channel.invokeMethod(METHOD_NAME_2, args,
//                object : MethodChannel.Result {
//                    override fun notImplemented() {
//
//                    }
//
//                    override fun error(p0: String?, p1: String?, p2: Any?) {
//
//                    }
//
//                    override fun success(p0: Any?) {
//                        Log.e("====> flutter result", p0.toString())
//                    }
//
//                })
//    }

}
