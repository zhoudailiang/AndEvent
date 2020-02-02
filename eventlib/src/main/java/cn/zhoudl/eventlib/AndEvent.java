package cn.zhoudl.eventlib;

import android.app.Application;

/**
 * Android 事件上传组建
 */
public class AndEvent {

    /**
     * 组建初始化
     */
    public static void init(Application application, EventConfig eventConfig) {
        if (application == null) {
            throw new IllegalArgumentException("AndEvent#init application mustn't null!!!");
        }
        Constants.sApplication = application;

        if (eventConfig == null) {
            throw new IllegalArgumentException("AndEvent#init eventConfig mustn't null!!!");
        }
        Constants.sEventConfig = eventConfig;
    }

    /**
     * 这里通过获取上传器后进行日志上报
     */
    public static IEventUploader getUploader() {
        return UploaderHolder.INSTANCE;
    }

    private static class UploaderHolder {
        private static IEventUploader INSTANCE = new EventUploaderImpl();
    }

}
