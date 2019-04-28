import 'dart:async';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:simple_hybrid_stack/routers.dart';

typedef RouteWidgetBuilder = Widget Function(BuildContext context, Map args);

class SimpleHybridStack {
  static const MethodChannel _channel =
  const MethodChannel('simple_hybrid_stack');


  static addRoute(String routeName, Widget routePage) {
    Routes.instance.addRoute(routeName, routePage);
  }

  ////////////////////// Flutter 调用 Native /////////////////////
  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<Widget> get initFlutterPage async {
    var result = await _channel.invokeMethod('initFlutterPage');
    Map<String, dynamic> response = Map<String, dynamic>.from(result);
    String routeName = response["routeName"];
    var args;
    if (response["args"] != null)
      args = Map<String, dynamic>.from(response["args"]);
    return Routes.instance.initPageRoute(routeName, args: args);
  }

  static Future openNativePage(String routeName, Map otherArgs) async {
    var args = {};
    args["routeName"] = routeName;
    args["args"] = otherArgs;
    var result = await _channel.invokeMethod('openNativePage', args);
    return result;
  }
////////////////////// Flutter 调用 Native /////////////////////

}
