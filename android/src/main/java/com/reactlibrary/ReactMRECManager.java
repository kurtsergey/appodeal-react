package com.reactlibrary;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule.RCTDeviceEventEmitter;

import android.support.annotation.Nullable;
import android.util.Log;
import com.appodeal.ads.MrecView;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.MrecCallbacks;
import android.app.Activity;
import android.graphics.Rect;

import java.util.Map;

public class ReactMRECManager extends SimpleViewManager<MrecView>{

  public static final String REACT_CLASS = "MrecView";
  private RCTEventEmitter mEventEmitter;
  private RCTDeviceEventEmitter mDeviceEventEmitter;
  
  public enum Events {
    EVENT_MREC_LOADED("onMrecLoaded"),
    EVENT_MREC_FAILED_TO_LOAD("onMrecFailedToLoad"),
    EVENT_MREC_SHOWN("onMrecShown"),
    EVENT_MREC_CLICKED("onMrecClicked");

    private final String mName;

    Events(final String name) {
      mName = name;
    }

    @Override
    public String toString() {
      return mName;
    }
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }
  
  @Override
  public MrecView createViewInstance(ThemedReactContext context) {
	mEventEmitter = context.getJSModule(RCTEventEmitter.class);
	mDeviceEventEmitter = context.getJSModule(RCTDeviceEventEmitter.class);
	MrecView mv = Appodeal.getMrecView(AppodealModule.getActivity());
	addCalbacks(mv);
    return mv;
  }
  
  private void sendEventToJS(String eventName, WritableMap params, int id){
	  Log.i("Appodeal", "event name: " + eventName + " start");
	  mDeviceEventEmitter.emit(eventName, params);
	  mEventEmitter.receiveEvent(id, eventName, params);
	  Log.i("Appodeal", "event name: " + eventName + " end");
  }
  
  private void addCalbacks(final MrecView mv){
	  Appodeal.setMrecCallbacks(new MrecCallbacks(){
			@Override
			public void onMrecLoaded(boolean isPrecache) {
				sendEventToJS(Events.EVENT_MREC_LOADED.toString(), null, mv.getId());
			}

			@Override
			public void onMrecFailedToLoad() {
				sendEventToJS(Events.EVENT_MREC_FAILED_TO_LOAD.toString(), null, mv.getId());
			}

			@Override
			public void onMrecShown() {
				sendEventToJS(Events.EVENT_MREC_SHOWN.toString(), null, mv.getId());
			}

			@Override
			public void onMrecClicked() {
				sendEventToJS(Events.EVENT_MREC_CLICKED.toString(), null, mv.getId());
			}
	  });
  }
  
  @Override
  @Nullable
  public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
    MapBuilder.Builder<String, Object> builder = MapBuilder.builder();
    for (Events event : Events.values()) {
      builder.put(event.toString(), MapBuilder.of("registrationName", event.toString()));
    }
    return builder.build();
  }
}