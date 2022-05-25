package id.accurate.pos.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.accurate.pos.data.local.entity.CityEntity
import id.accurate.pos.data.local.entity.UserEntity

@Database(entities = [UserEntity::class, CityEntity::class], version = 1, exportSchema = false)
abstract class ContentDatabase : RoomDatabase() {

    abstract fun contentDao() : ContentDao

    companion object{

        @Volatile
        private var INSTANCE : ContentDatabase? = null

        fun getInstance(context: Context) : ContentDatabase =
            INSTANCE ?: synchronized(this){
                Room.databaseBuilder(context.applicationContext, ContentDatabase::class.java, "AccuratePOS").build().apply {
                    INSTANCE = this
                }
            }
    }

}