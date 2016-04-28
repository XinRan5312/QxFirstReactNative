package com.xinran.qxfirstreactive;

import com.facebook.react.ReactActivity;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.xinran.qxfirstreactive.custompackage.CustomPackageToast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by qixinh on 16/4/28.
 */
public class MainActivity extends ReactActivity {

    /**
     * 返回注册JavaScript的主要组件的名称。
     这是用来安排渲染的组件。
     * @return
     * AppRegistry.registerComponent('commonModulsName', () => ModulesDemo);
     */
    @Override
    protected String getMainComponentName() {
        return "commonModulsName";
    }

    /**
     * 返回是否应启用开发模式。
     这使得如开发菜单。
     * @return
     */
    @Override
    protected boolean getUseDeveloperSupport() {
        return BuildConfig.DEBUG;
    }

    /**
     * 一个由应用程序使用的包列表。如果应用程序使用附加视图
        或模块除了默认的，在这里添加更多的包。
     * @return
     */
    @Override
    protected List<ReactPackage> getPackages() {
        return Arrays.<ReactPackage>asList(
                new MainReactPackage(),
                new CustomPackageToast()
        );
    }
}
