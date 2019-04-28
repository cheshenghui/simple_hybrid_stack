package com.oceanus.flutter_plugin_simple_open

import android.app.Activity
import android.content.Intent
import java.util.HashMap

/**
 * created by shenghuiche on 2019/4/24
 */
object Routers {

    private var routes: MutableMap<String, Class<*>> = HashMap()

    fun addRoute(routeName: String, clazz: Class<*>) {
        routes[routeName] = clazz
    }

    fun startAct(act: Activity, routeName: String? = "/", flutterArgs: HashMap<*, *>?) {
        act.startActivity(Intent(act, routes[routeName]).putExtra("args", flutterArgs))
    }

}