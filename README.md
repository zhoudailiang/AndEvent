# AndEvent

AndEvent 全称 Android Event ，是一款通用的上报组建。

## 1. 接入

### 1.1 初始化

在 Application `onCreate()` 方法中初始化

```java
AndEvent.init(this, new EventConfig() {
    // 设置一组上报的条数
    @Override
    public int getUploadGroupCount() {
        return 10;
    }

    // 实现网络上报（根据自己项目进行）
    @Override
    public void upload(List<EventEntity> dataList, IUploadCallback callback) {
        callback.onUploadSuccess(dataList);
    }
});
```

### 1.2 使用

#### 1.2.1  uploadNow()

实时上报，会立即写入数据库，然后取一组数据进行上报，不足一组就上报全部。

```java
AndEvent.getUploader().uploadNow(entity);
```

#### 1.2.2  uploadQueue()

非实时上报，写入数据库，达到设定一组条数后才上报。

```java
AndEvent.getUploader().uploadQueue(entity);
```

