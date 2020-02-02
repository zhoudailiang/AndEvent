package cn.zhoudl.eventlib;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface EventDao {

    @Query("SELECT COUNT(*) FROM EventEntity")
    int count();

    @Insert
    void insert(List<EventEntity> dataList);

    @Delete
    void delete(List<EventEntity> dataList);

    @Update
    void update(List<EventEntity> dataList);

    @Query("SELECT * FROM EventEntity WHERE status = 0 ORDER BY id DESC LIMIT :count")
    List<EventEntity> query(int count);
}
