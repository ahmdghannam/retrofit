package eng.ahmed.test

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NamesDao {

    @Insert
    suspend fun insert(name:Name)

    @Query("select * from names_table order by name asc")
     fun get():Flow<List<Name>>



}