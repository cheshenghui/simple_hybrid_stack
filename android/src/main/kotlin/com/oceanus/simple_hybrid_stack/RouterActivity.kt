package com.oceanus.simple_hybrid_stack

import android.os.Bundle
import io.flutter.app.FlutterActivity

/**
 * created by shenghuiche on 2019/4/16
 */
class RouterActivity: FlutterActivity() {

    companion object {
        const val EXTRA_ROUTE_NAME = "EXTRA_ROUTE_NAME"
        const val EXTRA_ARGS = "EXTRA_ARGS"
    }

    var routeName: String = ""
    var args: HashMap<String, Any>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        routeName = intent?.extras?.getString(EXTRA_ROUTE_NAME)?:""
        if (intent?.extras?.get(EXTRA_ARGS) != null) {
            args = intent?.extras?.get(EXTRA_ARGS) as HashMap<String, Any>
        }

        SimpleHybridStackPlugin.init(this, routeName, args)
    }
}