package com.yuecheng.im;

import android.app.Application;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nim.uikit.common.util.string.MD5;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.util.NIMUtil;

public class NimApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
		 // 初始化云信SDK
        NIMClient.init(this, new LoginInfo("shimiso", MD5.getStringMD5("8361718")), NimSDKOptionConfig.getSDKOptions(this));

        // 以下逻辑只在主进程初始化时执行
        if (NIMUtil.isMainProcess(this)) {
	         // 在主进程中初始化UI组件，判断所属进程方法请参见demo源码。
            initUiKit();
            // 初始化消息提醒
            NIMClient.toggleNotification(true);

	     }
	 }

    private void initUiKit() {
    	 // 初始化
        NimUIKit.init(this);
    }
}