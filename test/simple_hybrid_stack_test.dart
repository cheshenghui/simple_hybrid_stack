import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:simple_hybrid_stack/simple_hybrid_stack.dart';

void main() {
  const MethodChannel channel = MethodChannel('simple_hybrid_stack');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await SimpleHybridStack.platformVersion, '42');
  });
}
