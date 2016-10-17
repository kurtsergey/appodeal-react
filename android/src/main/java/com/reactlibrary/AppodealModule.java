package com.reactlibrary;

import android.widget.Toast;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;
import com.facebook.react.bridge.Arguments;
import java.util.Map;
import java.util.HashMap;
import android.content.pm.PackageManager;

import com.appodeal.ads.Appodeal;
import com.appodeal.ads.UserSettings;
import com.appodeal.ads.BannerCallbacks;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.NonSkippableVideoCallbacks;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.appodeal.ads.SkippableVideoCallbacks;
import com.appodeal.ads.utils.PermissionsHelper.AppodealPermissionCallbacks;
import com.appodeal.ads.utils.Log;

public class AppodealModule extends ReactContextBaseJavaModule implements InterstitialCallbacks, BannerCallbacks, SkippableVideoCallbacks, NonSkippableVideoCallbacks, RewardedVideoCallbacks, AppodealPermissionCallbacks{

  private final ReactApplicationContext reactContext;
  
  private static final String DURATION_SHORT_KEY = "SHORT";
  private static final String DURATION_LONG_KEY = "LONG";
  
  private Callback accessCoarseLocation; 
  private Callback writeExternalStorage;
  
  private UserSettings settings;

  public AppodealModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
	Appodeal.setInterstitialCallbacks(this);
	Appodeal.setBannerCallbacks(this);
	Appodeal.setSkippableVideoCallbacks(this);
	Appodeal.setNonSkippableVideoCallbacks(this);
	Appodeal.setRewardedVideoCallbacks(this);
  }

  @Override
  public String getName() {
    return "Appodeal";
  }
  
  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    constants.put(DURATION_SHORT_KEY, Toast.LENGTH_SHORT);
    constants.put(DURATION_LONG_KEY, Toast.LENGTH_LONG);
	constants.put("NONE", Appodeal.NONE);
	constants.put("INTERSTITIAL", Appodeal.INTERSTITIAL);
	constants.put("BANNER", Appodeal.BANNER);
	constants.put("BANNER_TOP", Appodeal.BANNER_TOP);
	constants.put("BANNER_BOTTOM", Appodeal.BANNER_BOTTOM);
	constants.put("NON_SKIPPABLE_VIDEO", Appodeal.NON_SKIPPABLE_VIDEO);
	constants.put("SKIPPABLE_VIDEO", Appodeal.SKIPPABLE_VIDEO);
	constants.put("REWARDED_VIDEO", Appodeal.REWARDED_VIDEO);
	constants.put("GENDER_MALE", UserSettings.Gender.MALE.name());
	constants.put("GENDER_FEMALE", UserSettings.Gender.FEMALE.name());
	constants.put("GENDER_OTHER", UserSettings.Gender.OTHER.name());
	constants.put("ALCOHOL_NEUTRAL", UserSettings.Alcohol.NEUTRAL.name());
	constants.put("ALCOHOL_NEGATIVE", UserSettings.Alcohol.NEGATIVE.name());
	constants.put("ALCOHOL_POSITIVE", UserSettings.Alcohol.POSITIVE.name());
	constants.put("SMOKING_NEUTRAL", UserSettings.Smoking.NEUTRAL.name());
	constants.put("SMOKING_NEGATIVE", UserSettings.Smoking.NEGATIVE.name());
	constants.put("SMOKING_POSITIVE", UserSettings.Smoking.POSITIVE.name());
	constants.put("OCCUPATION_OTHER", UserSettings.Occupation.OTHER.name());
	constants.put("OCCUPATION_SCHOOL", UserSettings.Occupation.SCHOOL.name());
	constants.put("OCCUPATION_UNIVERSITY", UserSettings.Occupation.UNIVERSITY.name());
	constants.put("OCCUPATION_WORK", UserSettings.Occupation.WORK.name());
	constants.put("RELATION_DATING", UserSettings.Relation.DATING.name());
	constants.put("RELATION_ENGAGED", UserSettings.Relation.ENGAGED.name());
	constants.put("RELATION_MARRIED", UserSettings.Relation.MARRIED.name());
	constants.put("RELATION_OTHER", UserSettings.Relation.OTHER.name());
	constants.put("RELATION_SEARCHING", UserSettings.Relation.SEARCHING.name());
	constants.put("RELATION_SINGLE", UserSettings.Relation.SINGLE.name());
	constants.put("LOG_LEVEL_VERBOSE", Log.LogLevel.verbose.name());
	constants.put("LOG_LEVEL_DEBUG", Log.LogLevel.debug.name());
	constants.put("LOG_LEVEL_NONE", Log.LogLevel.none.name());
    return constants;
  }
  
  @ReactMethod
  public void showToast(String message, int duration) {
    Toast.makeText(getReactApplicationContext(), message, duration).show();
  }
  
  @ReactMethod
  public void initialize(String appKey, int adTypes){
	  Appodeal.initialize(getCurrentActivity(), appKey, adTypes);
  }
  
  @ReactMethod
  public void setAutoCache(int adTypes, boolean isEnabled){
	Appodeal.setAutoCache(adTypes, isEnabled);
  }
  
  @ReactMethod
  public void cache(int adTypes){
	  Appodeal.cache(getCurrentActivity(), adTypes);
  }
  
  @ReactMethod
  public void isLoaded(int adTypes, Callback callback){
	boolean result =  Appodeal.isLoaded(adTypes);
	if(callback != null)
		callback.invoke(result);
  }

  @ReactMethod
  public void isPrecache(int adType, Callback callback){
	boolean result = Appodeal.isPrecache(adType);
	if(callback != null)
		callback.invoke(result);
  }
  
  @ReactMethod
  public void show(ReadableMap args, Callback callback){
	int adType = args.getInt("adType");
	String placement = args.hasKey("placement") ? args.getString("placement") : null;
	boolean result;
	if(placement == null)
		result = Appodeal.show(getCurrentActivity(), adType);
	else
		result = Appodeal.show(getCurrentActivity(), adType, placement);
	if(callback != null)
		callback.invoke(result);
  }
  
  @ReactMethod
  public void hide(int adTypes){
	Appodeal.hide(getCurrentActivity(), adTypes);	
  }
  
  @ReactMethod
  public void confirm(int adTypes){
	Appodeal.confirm(adTypes);
  }
  
  @ReactMethod
  public void disableLocationPermissionCheck(){
	Appodeal.disableLocationPermissionCheck();
  }

  @ReactMethod
  public void disableWriteExternalStoragePermissionCheck(){
	Appodeal.disableWriteExternalStoragePermissionCheck();
  }
  
  @ReactMethod
  public void disableNetwork(ReadableMap args){
	String networkName = args.getString("network");
	int adTypes = args.hasKey("adType") ? args.getInt("adType") : -1;
	if(adTypes == -1)
		Appodeal.disableNetwork(getCurrentActivity(), networkName);
	else
		Appodeal.disableNetwork(getCurrentActivity(), networkName, adTypes);
  }
  
  @ReactMethod
  public void set728x90Banners(boolean flag){
	Appodeal.set728x90Banners(flag);
  }
  
  @ReactMethod
  public void setSmartBanners(boolean flag){
	Appodeal.setSmartBanners(flag);
  }
  
  @ReactMethod
  public void setBannerAnimation(boolean flag){
	Appodeal.setBannerAnimation(flag);
  }
  
  @ReactMethod
  public void setCustomRule(String name, String value){
	Appodeal.setCustomRule(name, value);
  }
  
  @ReactMethod
  public void setTesting(boolean flag){
	Appodeal.setTesting(flag);
  }
  
  @ReactMethod
  public void setLogLevel(String level){
	Appodeal.setLogLevel(Log.LogLevel.valueOf(level));
  }
  
  @ReactMethod
  public void setOnLoadedTriggerBoth(int adTypes, boolean flag){
	Appodeal.setOnLoadedTriggerBoth(adTypes, flag);
  }
  
  @ReactMethod
  public void trackInAppPurchase(double amount, String currency){
	Appodeal.trackInAppPurchase(getCurrentActivity(), amount, currency);
  }
  
  private UserSettings getUserSettings(){
	  if(settings == null)
		  settings = Appodeal.getUserSettings(getCurrentActivity());
	  return settings;
  }
  
  @ReactMethod
  public void setAge(int age){
	  getUserSettings().setAge(age);
  }
  
  @ReactMethod
  public void setBirthday(String bDay){
	getUserSettings().setBirthday(bDay);
  }
  
  @ReactMethod
  public void setEmail(String email){
	getUserSettings().setEmail(email);
  }
  
  @ReactMethod
  public void setInterests(String interests){
	getUserSettings().setInterests(interests);
  }
  
  @ReactMethod
  public void setUserId(String id){
	getUserSettings().setUserId(id);
  }
  
  @ReactMethod
  public void setGender(String gender){
	getUserSettings().setGender(UserSettings.Gender.valueOf(gender));
  }
  
  @ReactMethod
  public void setAlcohol(String alcohol){
	getUserSettings().setAlcohol(UserSettings.Alcohol.valueOf(alcohol));
  }
  
  @ReactMethod
  public void setSmoking(String smoking){
	getUserSettings().setSmoking(UserSettings.Smoking.valueOf(smoking));
  }
  
  @ReactMethod
  public void setOccupation(String occupation){
	getUserSettings().setOccupation(UserSettings.Occupation.valueOf(occupation));
  }
  
  @ReactMethod
  public void setRelation(String relation){
	getUserSettings().setRelation(UserSettings.Relation.valueOf(relation));
  }
  
  private void sendEventToJS(String eventName, WritableMap params){
	  reactContext.getJSModule(RCTDeviceEventEmitter.class).emit(eventName, params);
  }
  
  @Override
	public void onInterstitialClicked() {
		// TODO Auto-generated method stub
		sendEventToJS("onInterstitialClicked", null);
	}

	@Override
	public void onInterstitialClosed() {
		// TODO Auto-generated method stub
		sendEventToJS("onInterstitialClosed", null);
	}

	@Override
	public void onInterstitialFailedToLoad() {
		// TODO Auto-generated method stub
		sendEventToJS("onInterstitialFailedToLoad", null);
	}

	@Override
	public void onInterstitialLoaded(boolean isPrecache) {
		// TODO Auto-generated method stub
		WritableMap params = Arguments.createMap();
		params.putBoolean("isPrecache", isPrecache);
		sendEventToJS("onInterstitialLoaded", params);
	}

	@Override
	public void onInterstitialShown() {
		// TODO Auto-generated method stub
		sendEventToJS("onInterstitialShown", null);
	}

	@Override
	public void onBannerClicked() {
		// TODO Auto-generated method stub
		sendEventToJS("onBannerClicked", null);
	}

	@Override
	public void onBannerFailedToLoad() {
		// TODO Auto-generated method stub
		sendEventToJS("onBannerFailedToLoad", null);
	}

	@Override
	public void onBannerLoaded(int height, boolean isPrecache) {
		// TODO Auto-generated method stub
		WritableMap params = Arguments.createMap();
		params.putInt("height", height);
		params.putBoolean("isPrecache", isPrecache);
		sendEventToJS("onBannerLoaded", params);
	}

	@Override
	public void onBannerShown() {
		// TODO Auto-generated method stub
		sendEventToJS("onBannerShown", null);
	}

	@Override
	public void onSkippableVideoClosed(boolean isFinished) {
		// TODO Auto-generated method stub
		WritableMap params = Arguments.createMap();
		params.putBoolean("isFinished", isFinished);
		sendEventToJS("onSkippableVideoClosed", params);
	}

	@Override
	public void onSkippableVideoFailedToLoad() {
		// TODO Auto-generated method stub
		sendEventToJS("onSkippableVideoFailedToLoad", null);
	}

	@Override
	public void onSkippableVideoFinished() {
		// TODO Auto-generated method stub
		sendEventToJS("onSkippableVideoFinished", null);
	}

	@Override
	public void onSkippableVideoLoaded() {
		// TODO Auto-generated method stub
		sendEventToJS("onSkippableVideoLoaded", null);
	}

	@Override
	public void onSkippableVideoShown() {
		// TODO Auto-generated method stub
		sendEventToJS("onSkippableVideoShown", null);
	}

	@Override
	public void onNonSkippableVideoClosed(boolean isFinished) {
		// TODO Auto-generated method stub
		WritableMap params = Arguments.createMap();
		params.putBoolean("isFinished", isFinished);
		sendEventToJS("onNonSkippableVideoClosed", params);
	}

	@Override
	public void onNonSkippableVideoFailedToLoad() {
		// TODO Auto-generated method stub
		sendEventToJS("onNonSkippableVideoFailedToLoad", null);
	}

	@Override
	public void onNonSkippableVideoFinished() {
		// TODO Auto-generated method stub
		sendEventToJS("onNonSkippableVideoFinished", null);
	}

	@Override
	public void onNonSkippableVideoLoaded() {
		// TODO Auto-generated method stub
		sendEventToJS("onNonSkippableVideoLoaded", null);
	}

	@Override
	public void onNonSkippableVideoShown() {
		// TODO Auto-generated method stub
		sendEventToJS("onNonSkippableVideoShown", null);
	}

	@Override
	public void onRewardedVideoClosed(boolean isFinished) {
		// TODO Auto-generated method stub
		WritableMap params = Arguments.createMap();
		params.putBoolean("isFinished", isFinished);
		sendEventToJS("onRewardedVideoClosed", params);
	}

	@Override
	public void onRewardedVideoFailedToLoad() {
		// TODO Auto-generated method stub
		sendEventToJS("onRewardedVideoFailedToLoad", null);
	}

	@Override
	public void onRewardedVideoFinished(int amount, String currency) {
		// TODO Auto-generated method stub
		WritableMap params = Arguments.createMap();
		params.putInt("amount", amount);
		params.putString("currency", currency);
		sendEventToJS("onRewardedVideoFinished", params);
	}

	@Override
	public void onRewardedVideoLoaded() {
		// TODO Auto-generated method stub
		sendEventToJS("onRewardedVideoLoaded", null);
	}

	@Override
	public void onRewardedVideoShown() {
		// TODO Auto-generated method stub
		sendEventToJS("onRewardedVideoShown", null);
	}
	
	@Override
	public void accessCoarseLocationResponse(int response) {
		// TODO Auto-generated method stub
		WritableMap params = Arguments.createMap();
		params.putBoolean("isGranted", response == PackageManager.PERMISSION_GRANTED);
		sendEventToJS("accessCoarseLocationResponse", params);
	}

	@Override
	public void writeExternalStorageResponse(int response) {
		// TODO Auto-generated method stub
		WritableMap params = Arguments.createMap();
		params.putBoolean("isGranted", response == PackageManager.PERMISSION_GRANTED);
		sendEventToJS("writeExternalStorageResponse", params);
	}
	
	@ReactMethod
	public void requestAndroidMPermissions(){
		Appodeal.requestAndroidMPermissions(getCurrentActivity(), this);
	}
  
}