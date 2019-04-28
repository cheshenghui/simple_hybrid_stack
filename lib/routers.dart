import 'package:flutter/material.dart';

class Routes {
  static Routes _singleton;

  Routes._internal();

  static Routes get instance {
    if (_singleton == null) {
      _singleton = Routes._internal();
    }
    return _singleton;
  }

  addRoute(String routeName, Widget w) {
    _routes[routeName] = w;
  }

  Map<String, Widget> _routes = Map();

  Future<Widget> initPageRoute(String routeName, {Map args}) async {
    var builder = _routes[routeName];
    if (builder == null) {
      builder = _RouteNotFoundPage(routeName);
    }
    return builder;
  }
}

class _RouteNotFoundPage extends StatelessWidget {

  final String routeName;

  _RouteNotFoundPage(this.routeName);

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Colors.white,
      child: Center(
          child: Text(
        "$routeName 页面丢失",
        style: TextStyle(color: Colors.black, fontSize: 24),
      )),
    );
  }
}
