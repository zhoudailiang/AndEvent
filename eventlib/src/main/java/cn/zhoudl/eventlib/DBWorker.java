package cn.zhoudl.eventlib;

import android.content.Context;

import androidx.room.Room;

import java.util.List;

/**
 * 数据库操作者
 */
class DBWorker {

    private static class Holder {
        private static DBWorker INSTANCE = new DBWorker();
    }

    static DBWorker getInstance() {
        return Holder.INSTANCE;
    }

    /**
     * 事件数据库
     */
    private EventDatabase mDatabase;

    /**
     * 构造函数
     */
    private DBWorker() {
        Context context = Constants.sApplication.getApplicationContext();
        mDatabase = Room.databaseBuilder(context, EventDatabase.class, "events").build();
    }

    /**
     * 查询数据库中的计数
     */
    int count() {
        return mDatabase.eventDao().count();
    }

    /**
     * 保存到数据库
     */
    void save(List<EventEntity> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        mDatabase.eventDao().insert(dataList);

        Logger.i("save to db");
    }

    /**
     * 删除数据库中的数据
     */
    void delete(List<EventEntity> dataList) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }
        mDatabase.eventDao().delete(dataList);

        Logger.i("delete list");
    }

    /**
     * 查询 count 条数据
     */
    List<EventEntity> query(int count) {
        if (count < 1) {
            return null;
        }
        return mDatabase.eventDao().query(count);
    }

    /**
     * 标记数据库中的数据是否在上传中
     */
    void markUploading(List<EventEntity> dataList, boolean uploading) {
        if (dataList == null || dataList.isEmpty()) {
            return;
        }

        for (EventEntity entity : dataList) {
            entity.setUploading(uploading);
        }

        mDatabase.eventDao().update(dataList);

        Logger.i("mark list " + uploading);
    }
}
