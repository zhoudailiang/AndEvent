package cn.zhoudl.eventlib;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class EventEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "status")
    private boolean isUploading;

    @ColumnInfo(name = "data")
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isUploading() {
        return isUploading;
    }

    public void setUploading(boolean uploading) {
        isUploading = uploading;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
