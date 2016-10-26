//
//  AppodealNativeAdViewManager.h
//  AppodealReactDemo
//
//  Created by Павел Дуняшев on 24/10/16.
//  Copyright © 2016 Facebook. All rights reserved.
//

#import "RCTViewManager.h"
#import <Foundation/Foundation.h>
#import <RCTBridgeModule.h>
#import <Appodeal/Appodeal.h>
#import <Appodeal/APDNativeAd.h>
#import <Appodeal/APDNativeAdLoader.h>

@interface AppodealNativeAdViewManager : RCTViewManager <APDNativeAdLoaderDelegate>

//nativeAd properties
@property (nonatomic) CGFloat width;
@property (nonatomic) CGFloat height;
@property (nonatomic) NSString* nativeTitle;
@property (nonatomic) NSString* nativeSubtitle;
@property (nonatomic) NSString* nativeDescriptionText;
@property (nonatomic) NSString* nativeCallToActionText;
@property (nonatomic) NSString* nativeContentRating;
@property (nonatomic) NSNumber* nativeStarRating;
@property (nonatomic) CGSize nativeImageSize;
@property (nonatomic) NSURL* nativeImageUrl;
@property (nonatomic) CGSize nativeIconImageSize;
@property (nonatomic) NSURL* nativeIconImageUrl;
@property (nonatomic) UIView* nativeAdChoicesView;
//end of nativeAd properties

@property (nonatomic, strong) APDNativeAd* ad;
@property (nonatomic, strong) APDNativeAdLoader* adLoader;
@property (nonatomic, strong) UIView* AppodealNativeView;
@property (nonatomic) APDMediaView *mediaView;
@property (nonatomic) BOOL isMediaActive;
@property (nonatomic) NSInteger adViewType;
@property (nonatomic) CGFloat x;
@property (nonatomic) CGFloat y;

@end
