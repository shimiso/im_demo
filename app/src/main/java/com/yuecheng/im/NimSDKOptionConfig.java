package com.yuecheng.im;

import android.content.Context;

import com.netease.nim.uikit.business.session.viewholder.MsgViewHolderThumbBase;
import com.netease.nimlib.sdk.SDKOptions;

/**
 * Created by hzchenkang on 2017/9/26.
 * <p>
 * 云信sdk 自定义的SDK选项设置
 */

class NimSDKOptionConfig {

    static SDKOptions getSDKOptions(Context context) {
        SDKOptions options = new SDKOptions();

        // 配置 APP 保存图片/语音/文件/log等数据的目录
//        options.sdkStorageRootPath = getAppCacheDir(context) + "/nim"; // 可以不设置，那么将采用默认路径

        // 配置是否需要预下载附件缩略图
        options.preloadAttach = true;

        // 配置附件缩略图的尺寸大小
        options.thumbnailSize = MsgViewHolderThumbBase.getImageMaxEdge();

        // 通知栏显示用户昵称和头像
//        options.userInfoProvider = new NimUserInfoProvider(DemoCache.getContext());

        // 定制通知栏提醒文案（可选，如果不定制将采用SDK默认文案）
//        options.messageNotifierCustomization = messageNotifierCustomization;

        // 在线多端同步未读数
        options.sessionReadAck = true;

        // 动图的缩略图直接下载原图
        options.animatedImageThumbnailEnabled = true;

        // 采用异步加载SDK
        options.asyncInitSDK = true;

        // 是否是弱IM场景
        options.reducedIM = false;

        // 是否检查manifest 配置，调试阶段打开，调试通过之后请关掉
        options.checkManifestConfig = false;

        // 是否启用群消息已读功能，默认关闭
        options.enableTeamMsgAck = true;

        // 打开消息撤回未读数-1的开关
        options.shouldConsiderRevokedMessageUnreadCount = true;

        // 云信私有化配置项
        options.appKey = "45c6af3c98409b18a84451215d0bdd6e";

        options.loginCustomTag = "登录自定义字段";
        return options;
    }

}
