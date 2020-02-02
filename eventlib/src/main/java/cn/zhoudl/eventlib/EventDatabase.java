package cn.zhoudl.eventlib;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {EventEntity.class}, version = 1)
abstract class EventDatabase extends RoomDatabase {

    abstract EventDao eventDao();

}
