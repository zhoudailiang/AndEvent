package cn.zhoudl.eventlib;

import java.util.List;

public interface IUploadCallback {

    /**
     * 上传成功
     *
     * @param dataList 上传成功的列表数据
     */
    void onUploadSuccess(List<EventEntity> dataList);

    /**
     * 上传失败
     *
     * @param dataList 上传失败的列表数据
     */
    void onUploadFailure(List<EventEntity> dataList);

}
