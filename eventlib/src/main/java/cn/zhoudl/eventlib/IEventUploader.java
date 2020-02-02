package cn.zhoudl.eventlib;

import java.util.List;

public interface IEventUploader {

    void uploadNow(EventEntity data);

    void uploadNow(List<EventEntity> dataList);

    void uploadQueue(EventEntity data);
    
}
