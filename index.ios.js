import React, { Component } from 'react';
import {
    AppRegistry,
    StyleSheet,
    Text,
    TouchableHighlight,
    View,
    NativeModules, //plugin
    NativeAppEventEmitter, //callbacks
    requireNativeComponent
} from 'react-native';

var Appodeal = NativeModules.AppodealPlugin;
var NativeAdViewManagerPlugin = NativeModules.AppodealNativeAdViewManager; //native ad plugin		 +
var AppodealNativeAdView;
var INTERSTITIAL = 1;
var SKIPPABLE_VIDEO = 2;
var BANNER = 4;
var BANNER_BOTTOM = 8;
var BANNER_TOP = 16;
var NATIVE_AD = 32;
var REWARDED_VIDEO = 128;
var NON_SKIPPABLE_VIDEO = 256;

//Banner callbacks
NativeAppEventEmitter.addListener(
                                  'onBannerLoaded',
                                  (reminder) => {
                                  console.log("onBannerLoaded")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onBannerShown',
                                  (reminder) => {
                                  console.log("onBannerShown")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onBannerClicked',
                                  (reminder) => {
                                  console.log("onBannerClicked")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onBannerFailedToLoad',
                                  (reminder) => {
                                  console.log("onBannerFailedToLoad")
                                  }
                                  );

//Interstitial callbacks
NativeAppEventEmitter.addListener(
                                  'onInterstitialLoaded',
                                  (reminder) => {
                                  console.log("onInterstitialLoaded")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onInterstitialFailedToLoad',
                                  (reminder) => {
                                  console.log("onInterstitialFailedToLoad")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onInterstitialClosed',
                                  (reminder) => {
                                  console.log("onInterstitialClosed")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onInterstitialClicked',
                                  (reminder) => {
                                  console.log("onInterstitialClicked")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onInterstitialShown',
                                  (reminder) => {
                                  console.log("onInterstitialShown")
                                  }
                                  );

//Non skippable callbacks
NativeAppEventEmitter.addListener(
                                  'onNonSkippableVideoLoaded',
                                  (reminder) => {
                                  console.log("onNonSkippableVideoLoaded")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onNonSkippableVideoFailedToLoad',
                                  (reminder) => {
                                  console.log("onNonSkippableVideoFailedToLoad")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onNonSkippableVideoShown',
                                  (reminder) => {
                                  console.log("onNonSkippableVideoShown")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onNonSkippableVideoClosed',
                                  (reminder) => {
                                  console.log("onNonSkippableVideoClosed")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onNonSkippableVideoFinished',
                                  (reminder) => {
                                  console.log("onNonSkippableVideoFinished")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onNonSkippableVideoClicked',
                                  (reminder) => {
                                  console.log("onNonSkippableVideoClicked")
                                  }
                                  );


//skippable callbacks
NativeAppEventEmitter.addListener(
                                  'onSkippableVideoLoaded',
                                  (reminder) => {
                                  console.log("onSkippableVideoLoaded")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onSkippableVideoFailedToLoad',
                                  (reminder) => {
                                  console.log("onSkippableVideoFailedToLoad")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onSkippableVideoShown',
                                  (reminder) => {
                                  console.log("onSkippableVideoShown")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onSkippableVideoClosed',
                                  (reminder) => {
                                  console.log("onSkippableVideoClosed")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onSkippableVideoFinished',
                                  (reminder) => {
                                  console.log("onSkippableVideoFinished")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onSkippableVideoClicked',
                                  (reminder) => {
                                  console.log("onSkippableVideoClicked")
                                  }
                                  );


//rewarded callbacks
NativeAppEventEmitter.addListener(
                                  'onRewardedVideoLoaded',
                                  (reminder) => {
                                  console.log("onRewardedVideoLoaded")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onRewardedVideoFailedToLoad',
                                  (reminder) => {
                                  console.log("onRewardedVideoFailedToLoad")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onRewardedVideoShown',
                                  (reminder) => {
                                  console.log("onRewardedVideoShown")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onRewardedVideoClosed',
                                  (reminder) => {
                                  console.log("onRewardedVideoClosed")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onRewardedVideoFinished',
                                  (reminder) => {
                                  console.log("onRewardedVideoFinished")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onRewardedVideoClicked',
                                  (reminder) => {
                                  console.log("onRewardedVideoClicked")
                                  }
                                  );
NativeAppEventEmitter.addListener(
                                  'onMediaViewStartPlaying',
                                  (reminder) => {
                                  console.log("onMediaViewStartPlaying")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onMediaViewFinishPlaying',
                                  (reminder) => {
                                  console.log("onMediaViewFinishPlayingWasSkipped")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onMediaViewDidPresentFullScreenView',
                                  (reminder) => {
                                  console.log("onMediaViewDidPresentFullScreenView")
                                  }
                                  );
NativeAppEventEmitter.addListener(
                                  'onMediaViewDidDismissFullScreen',
                                  (reminder) => {
                                  console.log("onMediaViewDidDismissFullScreen")
                                  }
                                  );

NativeAppEventEmitter.addListener(
                                  'onNativeAdDidLoad',
                                  (reminder) => {
                                  console.log("nativeTitle: " + reminder.nativeTitle)
                                  console.log("nativeSubtitle: " + reminder.nativeSubtitle)
                                  console.log("nativeDescriptionText: " + reminder.nativeDescriptionText)
                                  console.log("nativeStarRating: " + reminder.nativeStarRating)
                                  console.log("nativeCallToActionText: " + reminder.nativeCallToActionText)
                                  console.log("nativeContentRating: " + reminder.nativeContentRating)
                                  console.log("nativeImageUrl: " + reminder.nativeImageUrl)
                                  console.log("nativeImageHeight: " + reminder.nativeImageHeight)
                                  console.log("nativeImageWidth: " + reminder.nativeImageWidth)
                                  console.log("nativeIconImageHeight: " + reminder.nativeIconImageHeight)
                                  console.log("nativeIconImageWidth: " + reminder.nativeIconImageWidth)
                                  console.log("nativeIconImageUrl: " + reminder.nativeIconImageUrl)

                                  }
                                  );

class AppodealReactDemo extends Component {

    constructor(props) {
        super(props);
        this.state = {
          showView: false
        };
    }

    pressButton1 () {
        Appodeal.initializeWithApiKey("dee74c5129f53fc629a44a690a02296694e3eef99f2d3a5f", INTERSTITIAL + BANNER + SKIPPABLE_VIDEO + NON_SKIPPABLE_VIDEO + REWARDED_VIDEO + NATIVE_AD);
        Appodeal.enableInterstitialCallbacks(true);
        Appodeal.setSmartBanners (true);
        Appodeal.setBannerBackground (true);
        Appodeal.setBannerAnimation (true);
        Appodeal.enableBannerCallbacks(true);
        Appodeal.enableSkippableVideoCallbacks(true);
        Appodeal.enableRewardedVideoCallbacks(true);
        Appodeal.enableNonSkippableVideoCallbacks(true);
        Appodeal.setUserId("12");
      }

    pressButton2 () {
        Appodeal.show(INTERSTITIAL,
            (result) => {
                    console.log(result);
            }
        );
    }

    pressButton3 () {
        Appodeal.show(BANNER_BOTTOM,
            (result) => {
                console.log(result);
            }
        );
    }
    pressButton4 () {
        Appodeal.show(BANNER_TOP,
            (result) => {
                console.log(result);
            }
        );
    }

    pressButton5 () {
        Appodeal.show(SKIPPABLE_VIDEO,
            (result) => {
                console.log(result);
            }
        );
    }

    pressButton6 () {
        Appodeal.show(NON_SKIPPABLE_VIDEO,
            (result) => {
                console.log(result);
            }
        );
    }

    pressButton7 () {
        Appodeal.show(REWARDED_VIDEO,
            (result) => {
                console.log(result);
            }
        );
    }

    pressButton8 () {
        Appodeal.disableNetworkType ("kAppodealChartboostNetworkName", INTERSTITIAL);
        Appodeal.setUserId ("12");
        Appodeal.setEmail ("hi@appodeal.com");
        Appodeal.setBirthday ("01-01-1991");
        Appodeal.setAge (20);
        Appodeal.setGender ("male");
        Appodeal.setOccupation ("university");
        Appodeal.setRelation ("single");
        Appodeal.setSmoking ("negative");
        Appodeal.setAlcohol ("negative");
        Appodeal.setInterests ("games");
        Appodeal.hide (INTERSTITIAL);
        Appodeal.setLogging (true);
        Appodeal.setTesting (false);
        Appodeal.getVersion ((result) => {
        console.log(result);
        });
        Appodeal.isLoaded (INTERSTITIAL,(result) => {
        console.log(result);
        });
        Appodeal.setCustomRule ("{\"valueNumber\":0,\"valueText\":\"text\"}");
        Appodeal.confirm (INTERSTITIAL);
        Appodeal.disableLocationPermissionCheck ();
        Appodeal.setAutoCache (INTERSTITIAL, true);
        Appodeal.isPrecache (INTERSTITIAL, (result) => {
        console.log(result);
        });
        Appodeal.isInitalized ((result) => {
        console.log(result);
        });
        Appodeal.showWithPlacement (INTERSTITIAL, "", (result) => {
        console.log(result);
        });
        Appodeal.cache (INTERSTITIAL);
    }
pressButton9() {
        NativeAdViewManagerPlugin.createNativeAdLoader();
       }
pressButton10() {
       NativeAdViewManagerPlugin.createNativeAdViewWithframe(0,0,250,150);
      }

pressButton11 () {
         NativeAdViewManagerPlugin.loadNativeAdWithType("APDNativeAdTypeAuto");
       }
pressButton12 () {
              NativeAdViewManagerPlugin.attachToView();

              }
pressButton13 () {
              AppodealNativeAdView = requireNativeComponent('AppodealNativeAdView', null);
              this.setState({showView: true});
              }
pressButton14 () {
              NativeAdViewManagerPlugin.attachAdChoicesViewIfExistsWithFrame(0,0,24,24);
              NativeAdViewManagerPlugin.addMediaViewToNativeAdWithFrame(0,0,250,150);
              }


  render() {

    return (
      <View style={styles.container}>
            <TouchableHighlight onPress={() => this.pressButton1()} style={styles.button} >
            <Text style={styles.buttonText}>
            INITIALIZEe
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton2()} style={styles.button} >
            <Text style={styles.buttonText}>
            SHOW INTERSTITIAL
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton3()} style={styles.button} >
            <Text style={styles.buttonText}>
            SHOW BANNER BOTTOM
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton4()} style={styles.button} >
            <Text style={styles.buttonText}>
            SHOW BANNER TOP
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton5()} style={styles.button} >
            <Text style={styles.buttonText}>
            SHOW SKIPPABLE VIDEO
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton6()} style={styles.button} >
            <Text style={styles.buttonText}>
            SHOW NON SKIPPABLE VIDEO
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton7()} style={styles.button} >
            <Text style={styles.buttonText}>
            SHOW REWARDED VIDEO
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton8()} style={styles.button} >
            <Text style={styles.buttonText}>
            TEST
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton9()} style={styles.button} >
            <Text style={styles.buttonText}>
            NativeAd create loader
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton10()} style={styles.button} >
            <Text style={styles.buttonText}>
            create native ad view
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton11()} style={styles.button} >
            <Text style={styles.buttonText}>
            load native ad
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton12()} style={styles.button} >
            <Text style={styles.buttonText}>
            attach native ad to view
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton13()} style={styles.button} >
            <Text style={styles.buttonText}>
            Show native ad view
            </Text>
            </TouchableHighlight>
            <TouchableHighlight onPress={() => this.pressButton14()} style={styles.button} >
            <Text style={styles.buttonText}>
            add media view to native ad
            </Text>
            </TouchableHighlight>



             {this.state.showView ? <AppodealNativeAdView style={{width: 320, height: 200, backgroundColor: 'red'}} /> : null}
      </View>
    );
  }
}

const styles = StyleSheet.create({
                                 container: {
                                 flex: 1,
                                 margin:90,
                                 justifyContent: 'flex-start',
                                 alignItems: 'center',
                                 },
                                 button:{
                                 height:20,
                                 width:200,
                                 borderColor: '#05A5D1',
                                 borderWidth:1,
                                 backgroundColor: '#3333',
                                 margin:3,
                                 justifyContent: 'center',
                                 alignItems:'center'
                                 },
                                 buttonText:{
                                 color: '#000000',
                                 fontSize:15,
                                 fontWeight: '100'
                                 }
});

AppRegistry.registerComponent('AppodealReactDemo', () => AppodealReactDemo);
