package com.xinran.qxfirstreactive.custommodule;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.uimanager.IllegalViewOperationException;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 然后继承ReactContextBaseJavaModule类之后，要求子类去实现一个getName()方法。
 * 该方法用于返回一个字符串信息，用来代表JavaScript前端使用这个模块。这边我们返回信息为
 * "QxCustomToast"用来代表给模块。这样我们在前端就可以用JavaScript通过
 * React.NativeModules.QxCustomToast访问到这个模块了。
 * Created by qixinh on 16/4/28.
 * <p/>
 * 加@ReactMethod注解的方法才能在js中被调用如下面的show和measureLayout
 */

/**
 * 有时候我们的App需要访问平台API，并且React Native可能还没有相应的模块包装；
 * 或者你需要复用一些Java代码，而不是用Javascript重新实现一遍；又或者你需要实现某些高性能的、
 * 多线程的代码，譬如图片处理、数据库、或者各种高级扩展等等。
 而用React Native可以在它的基础上编写真正原生的代码，并且可以访问平台所有的能力。
 如果React Native还不支持某个你需要的原生特性，你应当可以自己实现该特性的封装。
 */
public class CustomModuleUtils extends ReactContextBaseJavaModule {
    private static final String DURATION_SHORT = "SHORT";
    private static final String DURATION_LONG = "LONG";

    public CustomModuleUtils(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    /**
     * 其实就是返回类的别名，以便在javaScript中调用类中的方法，很想js和android之间的调用
     * onPress={()=>NativeModules.QxCustomToast.show("我是ToastCustomAndroid弹出消息",
     * NativeModules.QxCustomToast.SHORT)}
     *
     * @return
     */
    @Override
    public String getName() {
        return "QxCustomToast";
    }

    /**
     * 可选实现的方法为getConstants该给JavaScript提供一些可以使用常量，不过该方法不是必须实现的。
     * @return
     */
    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put(DURATION_SHORT, Toast.LENGTH_SHORT);
        constants.put(DURATION_LONG, Toast.LENGTH_LONG);
        return constants;
    }

    /**
     * 该方法用于给JavaScript进行调用
     *需要添加@ReactMethod注解，用于给JavaScript进行调用。该桥接方法的返回类型必须要为void。
     * React Native中的桥接通信是异步形式的，所以如果需要返回值给JavaScript必须需要通过回调方法或者事件发送
     * @param message
     * @param duration
     */
    @ReactMethod
    public void show(String message, int duration) {
        Toast.makeText(getReactApplicationContext(), message, duration).show();
    }

    @ReactMethod
    public void logE(String tag, String msg) {
        Log.e(tag, msg);
    }

    @ReactMethod
    public void logV(String tag, String msg) {
        Log.v(tag, msg);
    }

    @ReactMethod
    public void logW(String tag, String msg) {
        Log.w(tag, msg);
    }

    @ReactMethod
    public void logD(String tag, String msg) {
        Log.d(tag, msg);
    }
    @ReactMethod
    public void sharedPreferencesPut(String key,Object obj){
        SharedPreferences sharedPreferences=getReactApplicationContext().getSharedPreferences("qxSharedPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(obj instanceof String){
            editor.putString(key,(String)obj);
        }else if(obj instanceof Boolean){
            editor.putBoolean(key,(Boolean)obj);
        }
        editor.commit();
    }
    @ReactMethod
         public void cache(String dir,File file){

    }
    @ReactMethod
    public void loadImg(String url,ImageView img){

    }
    @ReactMethod
    public void requestNet(String url, com.squareup.okhttp.Callback callback){

    }
    /**
     * 这边只是演示相关回调方法的使用,所以这边的使用方法是非常简单的
     *
     * @param errorCallback   数据错误回调函数
     * @param successCallback 数据成功回调函数
     */
    @ReactMethod
    public void measureLayout(Callback errorCallback,
                              Callback successCallback) {
        try {
            successCallback.invoke(100, 100, 200, 200);
        } catch (IllegalViewOperationException e) {
            errorCallback.invoke(e.getMessage());
        }
    }
}
