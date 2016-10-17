# Appodeal React Native plugin

## 1. Link your Admob account
Appodeal yields optimal results in cooperation with Admob. To continue, you need to link your Admob account.

If you don't have Admob account, please sign up on Admob.com.

To link your Admob account to Appodeal, use the Chrome extension:

INSTALL EXTENSION


The extension source code is available at https://github.com/appodeal/admob-configurator.

The extension will make two changes in your Admob account. First, it will allow Appodeal to access your Admob reports over API, and second, it will create new ad units on Admob and submit them to Appodeal.

VIDEO TUTORIAL

## 2. Download SDK

You can download Appodeal React native plugin here.

Minimum OS requirements: Android API level 9 (Android OS 2.3).

Minimum React Native version: 0.29.0

Android Appodeal SDK version 1.15.5

iOS Appodeal SDK version x.x.x

## 3. AndroidManifest.xml

All required changes to AndroidManifest.xml are already included in the plugin

## 4. React Native Integration

You can add Appodeal plugin to your project with npm:

```
npm install react-native-appodeal --save`
```

### 4.1 Manual installation

Download the plugin and put it into `[YourProjectPath]/node_modules/react-native`

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.AppodealPackage;` to the imports at the top of the file
  - Add `new AppodealPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-appodeal'
  	project(':react-native-appodeal').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-appodeal/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-appodeal')
  	```

To enable cheetah network append following lines to `android/settings.gradle`:
```
include ':cheetah'
project(':cheetah').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-appodeal/android/cheetah-3.4.7')
```
After that add the following line  inside dependencies block in `android/app/build.gradle`:
```
compile project(':cheetah')
```

### 4.2. Ad types

Appodeal.INTERSTITIAL

Appodeal.SKIPPABLE_VIDEO

Appodeal.BANNER

Appodeal.REWARDED_VIDEO

Appodeal.NON_SKIPPABLE_VIDEO

Ad types can be combined using "|" operator. For example Appodeal.INTERSTITIAL | Appodeal.SKIPPABLE_VIDEO

Note that it is better to use NON_SKIPPABLE_VIDEO or REWARDED_VIDEO, but if you are sure you want to use SKIPPABLE_VIDEO you must confirm usage by calling Appodeal.confirm(Appodeal.SKIPPABLE_VIDEO) before SDK initialization

### 4.3. SDK initialization

Import Appodeal plugin to your project:

```
import Appodeal from 'react-native-appodeal'
```

To initialize SDK you need to add following code:

```
var appKey = "fee50c333ff3825fd6ad6d38cff78154de3025546d47a84f";
Appodeal.initialize(appKey, Appodeal.BANNER | Appodeal.INTERSTITIAL);
```

Note: appKey is the key you received when you created an app.

To initialize only interstitials use Appodeal.initialize(appKey, Appodeal.INTERSTITIALS);

To initialize only skippable videos use Appodeal.initialize(appKey, Appodeal.SKIPPABLE_VIDEO);

To initialize interstitials and videos use Appodeal.initialize(appKey, Appodeal.INTERSTITIALS | Appodeal.SKIPPABLE_VIDEO);

To initialize only banners use Appodeal.initialize(appKey, Appodeal.BANNER);

To initialize only rewarded video use Appodeal.initialize(appKey, Appodeal.REWARDED_VIDEO);

To initialize only non-skippable video use Appodeal.initialize(appKey, Appodeal.NON_SKIPPABLE_VIDEO);

### 4.4. Display ad

```
Appodeal.show({adType: adTypes, placement: "placement"}, callback);
```

show() returns in callback a boolean value indicating whether show call was passed to appropriate SDK

placement argument is optional.

To display interstitial use Appodeal.show({adType: Appodeal.INTERSTITIAL}, null)

To display skippable video use Appodeal.show({adType: Appodeal.SKIPPABLE_VIDEO}, null)

To display rewarded video use Appodeal.show({adType: Appodeal.REWARDED_VIDEO}, null)

To display non-skippable video use Appodeal.show({adType: Appodeal.NON_SKIPPABLE_VIDEO}, null)

To display interstitial or video use Appodeal.show({adType: Appodeal.INTERSTITIAL | Appodeal.SKIPPABLE_VIDEO}, null)

To display banner at the bottom of the screen use Appodeal.show({adType: Appodeal.BANNER_BOTTOM}, null)

To display banner at the top of the screen use Appodeal.show({adType: Appodeal.BANNER_TOP}, null)

### 4.5. Hiding banner

To hide banner you need to call the following code:

```
Appodeal.hide(Appodeal.BANNER);
```

### 4.6. Samples

Display interstitial after it was loaded

```
import { DeviceEventEmitter } from 'react-native';

DeviceEventEmitter.addListener('onInterstitialLoaded', (e) => Appodeal.show({adType: Appodeal.INTERSTITIAL}, null);
Appodeal.setAutoCache(Appodeal.INTERSTITIAL, false);
Appodeal.initialize(appKey, Appodeal.INTERSTITIAL);
Appodeal.cache(Appodeal.INTERSTITIAL);
```

Display interstitial after app launch

```
Appodeal.initialize(appKey, Appodeal.INTERSTITIAL);
Appodeal.show({adType: Appodeal.INTERSTITIAL}, null);
```

`Note: showing fullscreen ads immediately after app launch doesn't allowed. Also, if an ad has not uploaded yet, you will see a loader, which will be disappeared after few seconds`

## 5. Advanced features
#### Enabling test mode

```
Appodeal.setTesting(true);
```

In test mode test ads will be shown and debug data will be written to logcat

#### Enabling logging

```
Appodeal.setLogLevel(Appodeal.LOG_LEVEL_VERBOSE);

Possible values: Appodeal.LOG_LEVEL_VERBOSE, Appodeal.LOG_LEVEL_DEBUG, Appodeal.LOG_LEVEL_NONE.

```
Enable debug logging to logcat using tag "Appodeal"

#### Checking if ad is loaded

```
Appodeal.isLoaded(adTypes, (isLoaded) => alert(isLoaded));
```

To check if interstitial is loaded use Appodeal.isLoaded(Appodeal.INTERSTITIAL, (isLoaded) => alert(isLoaded))

To check if skippable video is loaded use Appodeal.isLoaded(Appodeal.SKIPPABLE_VIDEO, (isLoaded) => alert(isLoaded))

To check if rewarded video is loaded use Appodeal.isLoaded(Appodeal.REWARDED_VIDEO, (isLoaded) => alert(isLoaded))

To check if non-skippable video is loaded use Appodeal.isLoaded(Appodeal.NON_SKIPPABLE_VIDEO, (isLoaded) => alert(isLoaded))

To check if banner is loaded use Appodeal.isLoaded(Appodeal.BANNER, (isLoaded) => alert(isLoaded))

#### Checking if loaded ad is precache

```
Appodeal.isPrecache(adTypes, (isPrecache) => alert(isPrecache));
```

Currently supported only for interstitials, Use Appodeal.isPrecache(Appodeal.INTERSTITIAL, (isPrecache) => alert(isPrecache))

#### Setting Interstitial callbacks

To register callbacks you need ti import DeviceEventEmitter into your project

```
import { DeviceEventEmitter } from 'react-native';
```

It's possible to add following callbacks:

```
DeviceEventEmitter.addListener('onInterstitialLoaded', (e) => yourCallbackReceiver(e.isPrecache));
DeviceEventEmitter.addListener('onInterstitialClicked', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onInterstitialClosed', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onInterstitialFailedToLoad', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onInterstitialShown', (e) => yourCallbackReceiver());
```

#### Setting skippable video callbacks

To register callbacks you need ti import DeviceEventEmitter into your project

```
import { DeviceEventEmitter } from 'react-native';
```

It's possible to add following callbacks:

```
DeviceEventEmitter.addListener('onSkippableVideoClosed', (e) => yourCallbackReceiver(e.isFinished));
DeviceEventEmitter.addListener('onSkippableVideoFailedToLoad', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onSkippableVideoFinished', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onSkippableVideoLoaded', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onSkippableVideoShown', (e) => yourCallbackReceiver());
```

#### Setting rewarded video callbacks

To register callbacks you need ti import DeviceEventEmitter into your project

```
import { DeviceEventEmitter } from 'react-native';
```

It's possible to add following callbacks:

```
DeviceEventEmitter.addListener('onRewardedVideoClosed', (e) => yourCallbackReceiver(e.isFinished));
DeviceEventEmitter.addListener('onRewardedVideoFailedToLoad', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onRewardedVideoFinished', (e) => yourCallbackReceiver(e.amount, e.currency));
DeviceEventEmitter.addListener('onRewardedVideoLoaded', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onRewardedVideoShown', (e) => yourCallbackReceiver());
```

#### Setting non-skippable video callbacks

To register callbacks you need ti import DeviceEventEmitter into your project

```
import { DeviceEventEmitter } from 'react-native';
```

It's possible to add following callbacks:

```
DeviceEventEmitter.addListener('onNonSkippableVideoClosed', (e) => yourCallbackReceiver(e.isFinished));
DeviceEventEmitter.addListener('onNonSkippableVideoFailedToLoad', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onNonSkippableVideoFinished', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onNonSkippableVideoLoaded', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onNonSkippableVideoShown', (e) => yourCallbackReceiver());
```

#### Setting banner callbacks

To register callbacks you need ti import DeviceEventEmitter into your project

```
import { DeviceEventEmitter } from 'react-native';
```

It's possible to add following callbacks:

```
DeviceEventEmitter.addListener('onBannerClicked', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onBannerFailedToLoad', (e) => yourCallbackReceiver());
DeviceEventEmitter.addListener('onBannerLoaded', (e) => yourCallbackReceiver(e.height, e.isPrecache));
DeviceEventEmitter.addListener('onBannerShown', (e) => yourCallbackReceiver());
```

#### Manual ad caching

```
Appodeal.cache(adTypes);
```

You should disable automatic caching before SDK initialization using setAutoCache(adTypes, false).

To cache interstitial use Appodeal.cache(Appodeal.INTERSTITIAL)

To cache skippable video use Appodeal.cache(Appodeal.SKIPPABLE_VIDEO)

To cache rewarded video use Appodeal.cache(Appodeal.REWARDED_VIDEO)

To cache non-skippable video use Appodeal.cache(Appodeal.NON_SKIPPABLE_VIDEO)

To cache interstitial and video use Appodeal.cache(Appodeal.INTERSTITIAL | Appodeal.SKIPPABLE_VIDEO)

#### Enabling or disabling automatic caching

```
Appodeal.setAutoCache(adTypes, false);
```

Should be used before SDK initialization

To disable automatic caching for interstitials use Appodeal.setAutoCache(Appodeal.INTERSTITIAL, false)

To disable automatic caching for skippable videos use Appodeal.setAutoCache(Appodeal.SKIPPABLE_VIDEO, false)

To disable automatic caching for rewarded videos use Appodeal.setAutoCache(Appodeal.REWARDED_VIDEO, false)

To disable automatic caching for non-skippable videos use Appodeal.setAutoCache(Appodeal.NON_SKIPPABLE_VIDEO, false)

#### Triggering onLoaded callback twice

```
Appodeal.setOnLoadedTriggerBoth(adTypes, true);
```

Currently supported only for interstitials

setOnLoadedTriggerBoth(Appodeal.INTERSTITIAL, false) - onInterstitialLoaded will trigger only when normal ad was loaded (default).

setOnLoadedTriggerBoth(Appodeal.INTERSTITIAL, true) - onInterstitialLoaded will trigger twice, both when precache and normal ad were loaded.

Should be used before SDK initialization.

#### Disabling networks

```
Appodeal.disableNetwork({network: "networkName", adType: Appodeal.INTERSTITIAL});
```

Available network parameters: "amazon_ads", "applovin", "chartboost", "mopub", "unity_ads", "mailru", "facebook", "adcolony", "vungle", "yandex", "startapp", "avocarrot", "flurry", "pubnative", "cheetah", "inner-active", "revmob".

Parameter adType is optional. Network will be disabled for all ad types if not specified.

Should be used before SDK initialization

#### Remove adapters from Project

First of all, it's required  to disable network, which you want to remove.
```
Appodeal.disableNetwork({network: "startapp"});
```

After that, you can remove unused jar files from libs folder of Appodeal plugin. Some networks are included in appodeal-1.15.5.jar file. You can open it via an archiver and remove unused dex files from assets/dex folder.

#### Disabling location permission check

To disable toast messages ACCESS_COARSE_LOCATION permission is missing use the following method:

```
Appodeal.disableLocationPermissionCheck();
```

Should be used before SDK initialization.

`Note: disabling location permission may have a negative impact on your revenue`

#### Disabling write external storage permission check

To disable toast messages WRITE_EXTERNAL_STORAGE permission is missing use the following method:

```
Appodeal.disableWriteExternalStoragePermissionCheck();
```

Should be used before SDK initialization.

`Note: some networks don't work without write external storage permission. They will be disabled in your application.`

#### Tracking in-app purchase

```
Appodeal.trackInAppPurchase(amount, currencyCode);
```

Tracks in-app purchase information and sends info to our servers for analytics. Example:

```
Appodeal.trackInAppPurchase(5, "USD");
```

### 5.1. Setting user data.
Initialization

Our SDK provides the transfer of user data for better ad targeting and higher eCPM. All parameters are optional and can be defined partially.

Set the age of the user

Positive integer value.

```
Appodeal.setAge(25);
```

Set birth date

```
Appodeal.setBirthday("17/06/1990");
```

birthday in format "DD/MM/YYYY" or "MM/YYYY" or "YYYY"

Set user email

```
Appodeal.setEmail("hi@appodeal.com");
```

Specify gender of the user

```
Appodeal.setGender(Appodeal.GENDER_MALE);
```

Possible values: Appodeal.GENDER_MALE, Appodeal.GENDER_FEMALEE,Appodeal.GENDER_OTHER.

Set interests of the user

```
Appodeal.setInterests("reading, games, movies, snowboarding");
```

Specify occupation of the user

```
Appodeal.setOccupation(Appodeal.OCCUPATION_OTHER);
```

Possible values: Appodeal.OCCUPATION_WORK, Appodeal.OCCUPATION_UNIVERSITY, Appodeal.OCCUPATION_SCHOOL, Appodeal.OCCUPATION_OTHER

Specify marital status of the user

```
Appodeal.setRelation(Appodeal.RELATION_DATING);
```

Possible values: Appodeal.RELATION_DATING, Appodeal.RELATION_ENGAGED, Appodeal.RELATION_MARRIED, Appodeal.RELATION_SEARCHING, Appodeal.RELATION_SINGLE, Appodeal.RELATION_RELATION_OTHER

Set drinking habits of the user

```
Appodeal.setAlcohol(Appodeal.ALCOHOL_POSITIVE);
```

Possible values: Appodeal.ALCOHOL_NEGATIVE, Appodeal.ALCOHOL_NEUTRAL, Appodeal.ALCOHOL_POSITIVE.

User attitude to smoking

```
Appodeal.setSmoking(Appodeal.SMOKING_NEGATIVE);
```

Possible values: Appodeal.SMOKING_NEGATIVE, Appodeal.SMOKING_POSITIVE, Appodeal.SMOKING_NEUTRAL.

## 6. Changelog

1.15.5 (10.10.2016)
* Release