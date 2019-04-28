#import "SimpleHybridStackPlugin.h"
#import <simple_hybrid_stack/simple_hybrid_stack-Swift.h>

@implementation SimpleHybridStackPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftSimpleHybridStackPlugin registerWithRegistrar:registrar];
}
@end
