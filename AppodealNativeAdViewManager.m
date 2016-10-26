#import "AppodealNativeAdViewManager.h"
#import "RCTLog.h"
#import "RCTConvert.h"
#import "RCTBridge.h"
#import "RCTEventDispatcher.h"

@implementation AppodealNativeAdViewManager

RCT_EXPORT_MODULE();

@synthesize bridge = _bridge;

//return view to js, when import
- (UIView *)view
{
  return self.AppodealNativeView;
}

- (void)nativeAdLoader:(APDNativeAdLoader *)loader didFailToLoadWithError:(NSError *)error{
   [self.bridge.eventDispatcher sendAppEventWithName:@"nativeAdDidFailedToLoad" body:@{@"":@""}];
}



- (NSInteger)appodealAdViewTypeConvert:(NSString*) adViewType
{
  NSInteger adtype=0;
  if([adViewType isEqualToString:@"APDNativeAdTypeVideo"])
    adtype=APDNativeAdTypeVideo;
  if([adViewType isEqualToString:@"APDNativeAdTypeNoVideo"])
    adtype=APDNativeAdTypeNoVideo;
  if([adViewType isEqualToString:@"APDNativeAdTypeAuto"])
    adtype=APDNativeAdTypeAuto;
  return adtype;
}

RCT_EXPORT_METHOD(loadNativeAdWithType:(NSString *)adViewType)
{
  self.adViewType = [self appodealAdViewTypeConvert:adViewType];
  dispatch_async(dispatch_get_main_queue(), ^{
    [self.adLoader loadAdWithType:[self appodealAdViewTypeConvert:adViewType]];
  });
}

RCT_EXPORT_METHOD(createNativeAdLoader)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    self.adLoader = [APDNativeAdLoader new];
    self.adLoader.delegate = self;
  });
}


//callbacks

- (void)nativeAdLoader:(APDNativeAdLoader *)loader didLoadNativeAds:(NSArray <__kindof APDNativeAd *> *)nativeAds{
 /* [self.adLoader loadAdWithType:self.adViewType];
  [self.view setFrame:CGRectMake(self.x, self.y, self.width, self.height)];
  APDNativeAd * nativeAd = [nativeAds firstObject];
  if (nativeAd.adChoicesView) {
    self.ad = nativeAd;
    [self.view addSubview:nativeAd.adChoicesView];
  }*/
}

- (void)nativeAdLoader:(APDNativeAdLoader *)loader didLoadNativeAd:(APDNativeAd *)nativeAd{
  self.ad = nativeAd;
  
  
  self.nativeTitle = nativeAd.title;
  self.nativeSubtitle = nativeAd.subtitle;
  self.nativeDescriptionText = nativeAd.descriptionText;
  self.nativeCallToActionText = nativeAd.callToActionText;
  self.nativeContentRating = nativeAd.contentRating;
  self.nativeStarRating = nativeAd.starRating;
  self.nativeImageUrl = nativeAd.mainImage.url;
  self.nativeImageSize = nativeAd.mainImage.size;
  self.nativeIconImageUrl = nativeAd.iconImage.url;
  self.nativeIconImageSize = nativeAd.iconImage.size;
  self.nativeAdChoicesView = nativeAd.adChoicesView;
  if (self.nativeSubtitle == nil)
    self.nativeSubtitle = @"";
  if (self.nativeDescriptionText == nil)
    self.nativeDescriptionText = @"";
  if (self.nativeStarRating == nil)
    self.nativeStarRating = [NSNumber numberWithInt:0];
  if (self.nativeContentRating == nil)
    self.nativeContentRating = @"";
  NSDictionary *passProperties = @{
                              @"nativeTitle" : self.nativeTitle,
                              @"nativeSubtitle" : self.nativeSubtitle,
                              @"nativeDescriptionText" : self.nativeDescriptionText,
                              @"nativeStarRating" : self.nativeStarRating,
                              @"nativeCallToActionText" : self.nativeCallToActionText,
                              @"nativeContentRating" : self.nativeContentRating,
                              @"nativeImageUrl" : self.nativeImageUrl.absoluteString,
                              @"nativeImageHeight" : [NSNumber numberWithFloat:self.nativeImageSize.height],
                              @"nativeImageWidth" : [NSNumber numberWithFloat:self.nativeImageSize.width],
                              @"nativeIconImageHeight" : [NSNumber numberWithFloat:self.nativeIconImageSize.height],
                              @"nativeIconImageWidth" : [NSNumber numberWithFloat:self.nativeIconImageSize.width],
                              @"nativeIconImageUrl" : self.nativeIconImageUrl.absoluteString,
                              };
  [self updateMediaView];
  [self.bridge.eventDispatcher sendAppEventWithName:@"onNativeAdDidLoad" body:passProperties];

}

- (void)mediaViewStartPlaying:(APDMediaView *)mediaView {
  [self.bridge.eventDispatcher sendAppEventWithName:@"onMediaViewStartPlaying" body:@{@"":@""}];
}

- (void)mediaViewFinishPlaying:(APDMediaView *)mediaView videoWasSkipped:(BOOL)wasSkipped {
  [self.bridge.eventDispatcher sendAppEventWithName:@"onMediaViewFinishPlayingWasSkipped" body:@{@"":@""}];
}

- (void)mediaView:(APDMediaView *)mediaView didPresentFullScreenView:(UIView *)presentedView {
  [self.bridge.eventDispatcher sendAppEventWithName:@"onMediaViewDidPresentFullScreenView" body:@{@"":@""}];
}

- (void)mediaViewDidDismissFullScreen:(APDMediaView *)mediaView {
  [self.bridge.eventDispatcher sendAppEventWithName:@"onMediaViewDidDismissFullScreen" body:@{@"":@""}];
}

// end of callbacks


RCT_EXPORT_METHOD(attachToView)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    [self.ad attachToView:self.AppodealNativeView viewController:[[UIApplication sharedApplication] keyWindow].rootViewController];
  });
  
}

RCT_EXPORT_METHOD(addMediaViewToNativeAdWithFrame:(int)x y:(int)y width:(int)width height:(int)height)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    self.mediaView = [[APDMediaView alloc] initWithFrame:CGRectMake(x, y, width, height)];
    [self.mediaView setNativeAd:self.ad rootViewController:[[UIApplication sharedApplication] keyWindow].rootViewController];
    self.mediaView.delegate = self;
    [self.AppodealNativeView addSubview:self.mediaView];
  });
  
}

- (void)updateMediaView
{
  dispatch_async(dispatch_get_main_queue(), ^{
    [self.mediaView setNativeAd:self.ad rootViewController:[[UIApplication sharedApplication] keyWindow].rootViewController];
  });
}

RCT_EXPORT_METHOD(setMediaViewMuted:(BOOL)muted)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    [self.mediaView setMuted:muted];
  });
  
}

RCT_EXPORT_METHOD(setMediaViewSkippable:(BOOL)skippable)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    [self.mediaView setSkippable:skippable];
  });
  
}


RCT_EXPORT_METHOD(createNativeAdViewWithframe:(int)x y:(int)y width:(int)width heigth:(int)height)
{
  dispatch_async(dispatch_get_main_queue(), ^{
    self.x = x;
    self.y = y;
    self.width=width;
    self.height=height;
    self.AppodealNativeView = [[UIView alloc] init];
    [self.AppodealNativeView setFrame:CGRectMake(self.x, self.y, self.width, self.height)];

  });
}


@end
