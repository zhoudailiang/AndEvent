package cn.zhoudl.eventlib;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class EventConfig {

    /**
     * 默认线程池
     */
    private static ExecutorService sThreadPool = Executors.newSingleThreadExecutor();

    /**
     * 达到20个事件后触发上传
     */
    public int getUploadGroupCount() {
        return 20;
    }

    /**
     * 上传方法，由外部实现
     *
     * @param callback 上传回调
     * @param dataList 数据列表
     */
    public abstract void upload(List<EventEntity> dataList, IUploadCallback callback);

    /**
     * 异步线程池
     */
    public ExecutorService getThreadPool() {
        return sThreadPool;
    }

}
