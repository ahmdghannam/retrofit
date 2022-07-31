package eng.ahmed.test

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Name::class], exportSchema = false, version = 1)
abstract class NamesDatabase : RoomDatabase() {

    abstract fun namesDao(): NamesDao

    companion object {
        @Volatile
        private var INSTANCE: NamesDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope): NamesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NamesDatabase::class.java
                ,"theDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                
                INSTANCE=instance
                instance
            }
        }



    }

}