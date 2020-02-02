package cn.zhoudl.event.sample;

import android.app.Application;

import com.facebook.stetho.Stetho;

import java.util.List;

import cn.zhoudl.eventlib.AndEvent;
import cn.zhoudl.eventlib.EventConfig;
import cn.zhoudl.eventlib.EventEntity;
import cn.zhoudl.eventlib.IUploadCallback;

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        AndEvent.init(this, new EventConfig() {
            @Override
            public int getUploadGroupCount() {
                return 10;
            }

            @Override
            public void upload(List<EventEntity> dataList, IUploadCallback callback) {
                callback.onUploadSuccess(dataList);
            }
        });
    }
}
