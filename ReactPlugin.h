//
//  ReactMediationAdapter.h
//  demoAppodeal
//
//  Created by Denis on 26.05.16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Foundation/Foundation.h>
#import <RCTBridgeModule.h>
#import <Appodeal/Appodeal.h>
#import <Appodeal/AppodealNativeAd.h>
#import <Appodeal/AppodealNativeAdService.h>

//plugin
@interface ReactMediationAdapter : NSObject <RCTBridgeModule,AppodealNativeAdServiceDelegate>

@property (nonatomic, strong) AppodealNativeAdService* adService;
@property (nonatomic, strong) AppodealNativeAd* ad;
@property (nonatomic, strong) AppodealNativeAdViewAttributes* attributes;

@end
