package com.xinran.qxfirstreactive.custompackage;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.JavaScriptModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.xinran.qxfirstreactive.custommodule.CustomModuleUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 我们已经完成组件模块的定义以及对外方法的封装提供，下面在原生代码这边就需要进行注册该模块。
 * 我们需要自定义一个继承ReactPackage的类，同时需要实现这里边的三个方法。
 * 最重要的是在createNativeModule方法中添加刚刚那个模块。如果没有注册，那么当前模块在JavaScript中是无法被访问到的。
 *
 * 自定义的packager需要在MainActivity.java中的getPackagers方法中进行添加
 * Created by qixinh on 16/4/28.
 */
public class CustomPackageToast implements ReactPackage {
    /**
     * 完成模块注册
     * @param reactApplicationContext
     * @return
     */
    @Override
    public List<NativeModule> createNativeModules(ReactApplicationContext reactApplicationContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new CustomModuleUtils(reactApplicationContext));
        return modules;
    }

    @Override
    public List<Class<? extends JavaScriptModule>> createJSModules() {
        return Collections.emptyList();
    }

    @Override
    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
