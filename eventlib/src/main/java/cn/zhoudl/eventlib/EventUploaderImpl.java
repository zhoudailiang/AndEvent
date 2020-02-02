package cn.zhoudl.eventlib;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * 事件上传实现类
 */
class EventUploaderImpl implements IEventUploader {

    @Override
    public void uploadNow(EventEntity data) {
        if (data == null) {
            return;
        }

        List<EventEntity> dataList = new ArrayList<>();
        dataList.add(data);
        uploadNow(dataList);
    }

    @Override
    public void uploadNow(final List<EventEntity> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        Logger.i("uploadNow dataList");

        // 使用异步线程
        Constants.sEventConfig.getThreadPool().submit(new Runnable() {
            @Override
            public void run() {
                // 先保存
                DBWorker.getInstance().save(dataList);

                // 再查询一组，不足一组就查询所有
                int groupCount = Constants.sEventConfig.getUploadGroupCount();
                List<EventEntity> eventList = DBWorker.getInstance().query(groupCount);

                // 上传
                upload(eventList);
            }
        });
    }

    @Override
    public void uploadQueue(final EventEntity data) {
        if (data == null) {
            return;
        }

        Logger.i("uploadQueue data");

        // 使用异步线程
        Constants.sEventConfig.getThreadPool().submit(new Runnable() {
            @Override
            public void run() {
                // 先保存
                List<EventEntity> dataList = new ArrayList<>();
                dataList.add(data);
                DBWorker.getInstance().save(dataList);

                // 查询数据库中的条数
                int count = DBWorker.getInstance().count();
                Logger.i("count = " + count);

                int groupCount = Constants.sEventConfig.getUploadGroupCount();
                if (count < groupCount) {
                    return;
                }

                // 查询一组数据
                List<EventEntity> eventList = DBWorker.getInstance().query(groupCount);

                // 上传
                upload(eventList);
            }
        });
    }


    private void upload(List<EventEntity> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        Logger.i("start upload size = " + dataList.size());

        // 标记数据再上传中
        DBWorker.getInstance().markUploading(dataList, true);

        Constants.sEventConfig.upload(dataList, new IUploadCallback() {
            @Override
            public void onUploadSuccess(List<EventEntity> dataList) {
                Logger.i("onUploadSuccess");
                // 上传成功直接删除数据库中的数据
                DBWorker.getInstance().delete(dataList);

                // 继续查询是否大于触发上传的条数，如果是就就继续上传
                int remainCount = DBWorker.getInstance().count();
                int groupCount = Constants.sEventConfig.getUploadGroupCount();
                if (remainCount > groupCount) {
                    Logger.i("remain can upload");
                    upload(DBWorker.getInstance().query(groupCount));
                }
            }

            @Override
            public void onUploadFailure(List<EventEntity> dataList) {
                Logger.i("onUploadFailure");
                // 上传失败就标记数据没有再上传
                DBWorker.getInstance().markUploading(dataList, false);
            }
        });
    }

}