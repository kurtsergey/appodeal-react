package com.reactlibrary;

import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import android.support.annotation.Nullable;
import com.appodeal.ads.MrecView;
import com.appodeal.ads.Appodeal;
import android.app.Activity;
import android.graphics.Rect;
import android.util.Log;

public class ReactMRECManager extends SimpleViewManager<MrecView> {

  public static final String REACT_CLASS = "MrecView";

  @Override
  public String getName() {
    return REACT_CLASS;
  }
  
  @Override
  public MrecView createViewInstance(ThemedReactContext context) {
	  if(AppodealModule.getActivity() == null)
		  Log.i("wtf", "activity null");
    return Appodeal.getMrecView(AppodealModule.getActivity());
  }
  
  @ReactProp(name = "fix")
  public void setFix(MrecView view, @Nullable String src) {
	Log.i("Appodeal", "Mrec width: " + view.getWidth() + ", height: " + view.getHeight());
	int pos[] = new int[2];
	view.getLocationOnScreen(pos);
	Log.i("Appodeal", "Mrec x: " + pos[0] + ", y: " + pos[1]);
	Rect r = new Rect();
	boolean isVisible = view.getGlobalVisibleRect(r);
	Log.i("Appodeal", "Is mrec visible: " + isVisible);
  }
}