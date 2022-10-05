package de.bfpi.cordova.sqlite;

import android.database.CursorWindow;
import android.util.Log;

import java.lang.reflect.Field;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CursorWindowSize extends CordovaPlugin {
  private static final String LOG_TAG = "SQLite CursorWindowSize";

  private CallbackContext callbackContext;
  private JSONArray args;

  @Override
  public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
    this.args = args;
    this.callbackContext = callbackContext;
    if (action.equals("initialize")) {
      try {
        Log.v(LOG_TAG, "Increasing sCursorWindowSize to " + this.args.getString(0) + " MB");
        Field field = CursorWindow.class.getDeclaredField("sCursorWindowSize");
        field.setAccessible(true);
        Log.v(LOG_TAG, "sCursorWindowSize value before update: " + field.get(null));
        field.set(null, this.args.getInt(0) * 1024 * 1024);
        Log.v(LOG_TAG, "sCursorWindowSize value after update: " + field.get(null) + " Byte");
      } catch (Exception e) {
        Log.e(LOG_TAG, "Unexpected error increasing sCursorWindowSize", e);
      }
      return true;
    }
    return false;
  }
}
