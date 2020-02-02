package cn.zhoudl.event.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.zhoudl.eventlib.AndEvent;
import cn.zhoudl.eventlib.EventEntity;

public class MainActivity extends AppCompatActivity {

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.upload_now).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventEntity entity = new EventEntity();
                entity.setData("Content : " + count++);
                AndEvent.getUploader().uploadNow(entity);
            }
        });

        findViewById(R.id.upload_queue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventEntity entity = new EventEntity();
                entity.setData("Content : " + count++);
                AndEvent.getUploader().uploadQueue(entity);
            }
        });
    }
}
